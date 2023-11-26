import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes, provideRouter, withComponentInputBinding } from '@angular/router';
import { FleetManagementPageComponent } from './fleet-management/pages/fleet-management-page/fleet-management-page.component';
import { FleetViewPageComponent } from './fleet-management/pages/fleet-view-page/fleet-view-page.component';
import { CarAssigmentPageComponent } from './car-management/pages/car-assigment-page/car-assigment-page.component';
import { SchemaManagementPageComponent } from './schema-management/pages/schema-management-page/schema-management-page.component';
import { CarReportingPageComponent } from './car-reporting/pages/car-reporting-page/car-reporting-page.component';
import { UserCreationPageComponent } from './user-management/pages/user-creation-page/user-creation-page.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { LoginPageComponent } from './login/pages/login-page/login-page.component';
import { AppComponent } from './app.component';

const routes: Routes = 
[
  { 
    path: '',
    component: AppComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'fleet', 
    component: FleetManagementPageComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'fleet/:id/details', 
    component: FleetViewPageComponent,
    canActivate:  [AuthGuard]
  },
  { 
    path: 'newcomers', 
    component: CarAssigmentPageComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'schema', 
    component: SchemaManagementPageComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'report/:id', 
    component: CarReportingPageComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'users', 
    component: UserCreationPageComponent,
    canActivate: [AuthGuard]
  },
  { 
    path: 'login', 
    component: LoginPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    provideRouter(routes, withComponentInputBinding()),
  ]
})
export class AppRoutingModule { }
