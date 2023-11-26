import { Injectable }     from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {
    canActivate() {
        return this.loginService.login().pipe(tap(login => {
            if (!login) {
                this.router.navigate(['login']);
            }
        }))
    }
    //Constructor 
    constructor(private router: Router, private loginService : LoginService) { }
}