import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FleetManagementPageComponent } from './fleet-management/pages/fleet-management-page/fleet-management-page.component';

import { MaterialModule } from './shared/modules/material/material.module';
import { SidebarComponent } from './shared/components/sidebar/sidebar.component';
import { FleetManagementCreateComponent } from './fleet-management/components/fleet-management-create/fleet-management-create.component';
import { FleetOverviewComponent } from './fleet-management/components/fleet-overview/fleet-overview.component';
import { FleetViewPageComponent } from './fleet-management/pages/fleet-view-page/fleet-view-page.component';
import { CarAssigmentPageComponent } from './car-management/pages/car-assigment-page/car-assigment-page.component';
import { CarFleetAssigmentComponent } from './car-management/components/car-fleet-assigment/car-fleet-assigment.component';
import { CarOverviewComponent } from './car-management/components/car-overview/car-overview.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    FleetManagementPageComponent,
    FleetManagementCreateComponent,
    SidebarComponent,
    FleetOverviewComponent,
    FleetViewPageComponent,
    CarAssigmentPageComponent,
    CarFleetAssigmentComponent,
    CarOverviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
