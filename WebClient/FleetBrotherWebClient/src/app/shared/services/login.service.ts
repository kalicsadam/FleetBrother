import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map, tap } from 'rxjs';
import { Login } from 'src/app/data/dto/login.dto';
import { User } from 'src/app/data/dto/user.dto';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private static LOGIN_TOKEN_KEY = "TOKEN"

  constructor(private http : HttpClient) { }
  private _token: string = ""
  private _user: User | undefined;

  get token() {
    return this._token
  }

  get user(): User | undefined {
    if(this.isSuccess(this._token)){
      var jwtDecoded = JSON.parse(window.atob(this.token.split('.')[1]));
      return {
        id: 0,
        email: jwtDecoded.email,
        isAdmin: jwtDecoded.role == "ADMIN"
      }
    }
    return undefined;
  }

  login() {
    var token = this.retrieveLastToken()
    if (token != null) {
      this._token = token!
      return this.sendLoginTokenRequest(token).pipe(tap(
        success => {
          if (success) {
            this._token = token!
          } else {
            this._token = ""
            this.clearToken()
          }
        })
      )
    }
    return new BehaviorSubject(false)
  }

  loginWithCredentials(username: string, password: string) {
    return this.sendLoginCredentialsRequest(username, password).pipe(tap(
      login => {
        if (this.isSuccess(login.token)) {
          this._token = login.token
          this.saveToken(login.token)
        }
      }
    )).pipe(map(login => this.isSuccess(login.token)))
  }

  private retrieveLastToken() {
    var lastToken = localStorage.getItem(LoginService.LOGIN_TOKEN_KEY)
    return lastToken;
  }

  private saveToken(token: string) {
    var lastToken = localStorage.setItem(LoginService.LOGIN_TOKEN_KEY, token)
    return lastToken;
  }

  private sendLoginTokenRequest(token: string): Observable<boolean> {
    return this.http.post("/api/auth/checkToken", null,{ observe: 'response' }).pipe(map(response => response.ok))
  }

  private sendLoginCredentialsRequest(email: string, password: string): Observable<Login> {
    return this.http.post<Login>("/api/auth/login", {email: email, password: password})
  }

  private clearToken() {
    localStorage.removeItem(LoginService.LOGIN_TOKEN_KEY)
  }

  private isSuccess(token: string){
    return token != null && token != ""
  }
}
