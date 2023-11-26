import { NgModule } from '@angular/core';
import { RouterModule, Routes, provideRouter, withComponentInputBinding } from '@angular/router';
import { FleetManagementPageComponent } from './fleet-management/pages/fleet-management-page/fleet-management-page.component';
import { FleetViewPageComponent } from './fleet-management/pages/fleet-view-page/fleet-view-page.component';
import { CarAssigmentPageComponent } from './car-management/pages/car-assigment-page/car-assigment-page.component';
import { SchemaManagementPageComponent } from './schema-management/pages/schema-management-page/schema-management-page.component';
import { CarReportingPageComponent } from './car-reporting/pages/car-reporting-page/car-reporting-page.component';
import { UserCreationPageComponent } from './user-management/pages/user-creation-page/user-creation-page.component';

const routes: Routes = 
[
  { 
    path: 'fleet', 
    component: FleetManagementPageComponent
  },
  { 
    path: 'fleet/:id/details', 
    component: FleetViewPageComponent
  },
  { 
    path: 'newcomers', 
    component: CarAssigmentPageComponent
  },
  { 
    path: 'schema', 
    component: SchemaManagementPageComponent
  },
  { 
    path: 'report/:id', 
    component: CarReportingPageComponent
  },
  { 
    path: 'users', 
    component: UserCreationPageComponent
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
