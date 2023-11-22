import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FleetManagementPageComponent } from './fleet-management/pages/fleet-management-page/fleet-management-page.component';

import { MaterialModule } from './material/material.module';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FleetManagementCreateComponent } from './fleet-management/components/fleet-management-create/fleet-management-create.component';
import { FleetOverviewComponent } from './fleet-management/components/fleet-overview/fleet-overview.component';

@NgModule({
  declarations: [
    AppComponent,
    FleetManagementPageComponent,
    FleetManagementCreateComponent,
    SidebarComponent,
    FleetOverviewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
