import { Injectable }     from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { map, switchMap, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AdminGuard implements CanActivate {
    canActivate() {
        return this.loginService.login().pipe(tap(login => {
            if (!login|| this.loginService.user == undefined || !this.loginService.user.isAdmin) {
                this.router.navigate(['login']);
            }
        })).pipe(map(user=> user != null))
    }
    //Constructor 
    constructor(private router: Router, private loginService : LoginService) { }
}