import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FleetManagementPageComponent } from './fleet-management/pages/fleet-management-page/fleet-management-page.component';

const routes: Routes = 
[
  { 
    path: 'fleet', 
    component: FleetManagementPageComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
