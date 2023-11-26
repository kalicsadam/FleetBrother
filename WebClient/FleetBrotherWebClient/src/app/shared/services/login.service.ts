import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map, tap } from 'rxjs';
import { Login } from 'src/app/data/dto/login.dto';
import { User } from 'src/app/data/dto/user.dto';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private static LOGIN_TOKEN_KEY = "TOKEN"

  constructor() { }
  private _token: string = ""
  private _user: User | undefined;

  get token() {
    return this._token
  }

  get user(): User | undefined {
    return this._user
  }

  login() {
    var token = this.retrieveLastToken()
    if (token != null) {
      return this.sendLoginTokenRequest(token).pipe(tap(
        success => {
          if (success) {
            this._token = token!
            this.fetchUserData()
          } else {
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
        if (login.isSuccess) {
          this._token = login.token
          this.saveToken(login.token)
          this.fetchUserData()
        }
      }
    )).pipe(map(login => login.isSuccess))
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
    return new BehaviorSubject(true)
  }

  private sendLoginCredentialsRequest(username: string, password: string): Observable<Login> {
    return new BehaviorSubject({
      isSuccess: true,
      token: "token"
    })
  }

  private fetchUserData() {
    this._user = {
      id: 0,
      username: "Placeholder",
      isAdmin: true
    }
  }

  private clearToken() {
    localStorage.removeItem(LoginService.LOGIN_TOKEN_KEY)
  }
}