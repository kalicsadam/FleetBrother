import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/shared/services/login.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  constructor(
    private loginService : LoginService,
    private router : Router
    ){
    
  }
  inputUsername : string = ""
  inputPassword : string = ""

  ngOnInit(): void {
    this.loginService.login().subscribe(
      loggedIn => {
        if(loggedIn){
          this.onLoggedIn()
        }
      }
    );
  }

  onSubmit(){
    if(this.inputUsername != null && this.inputPassword != null){
      this.loginService.loginWithCredentials(this.inputUsername, this.inputPassword).subscribe(
        loggedIn => {
          if(loggedIn){
            this.onLoggedIn()
          }
        }
      )
    }
  }

  onLoggedIn(){
    this.router.navigate(["/"])
  }
}
