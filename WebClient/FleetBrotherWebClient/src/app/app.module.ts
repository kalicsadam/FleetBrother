import { NgModule, importProvidersFrom } from '@angular/core';
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
import { MessageDialogComponent } from './shared/components/message-dialog/message-dialog.component';
import { SchemaManagementPageComponent } from './schema-management/pages/schema-management-page/schema-management-page.component';
import { SchemaCreateComponent } from './schema-management/components/schema-create/schema-create.component';
import { SchemaOverviewComponent } from './schema-management/components/schema-overview/schema-overview.component';
import { FieldOverviewComponent } from './schema-management/components/field-overview/field-overview.component';
import { FieldCreationComponent } from './schema-management/components/field-creation/field-creation.component';
import { CarReportingPageComponent } from './car-reporting/pages/car-reporting-page/car-reporting-page.component';
import { SchemaCarAssigmentComponent } from './schema-management/components/schema-car-assigment/schema-car-assigment.component';
import { ReportComponent } from './car-reporting/components/report/report.component';
import { AlertsManagerComponent } from './car-reporting/components/alerts-manager/alerts-manager.component';
import { AlertOverviewComponent } from './car-reporting/components/alert-overview/alert-overview.component';
import { AlertCreationComponent } from './car-reporting/components/alert-creation/alert-creation.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoadingInterceptor } from './shared/interceptor/loading-interceptor';
import { UserCreationPageComponent } from './user-management/pages/user-creation-page/user-creation-page.component';
import { UserOverviewComponent } from './user-management/components/user-overview/user-overview.component';
import { UserCreateComponent } from './user-management/components/user-create/user-create.component';
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
    CarOverviewComponent,
    MessageDialogComponent,
    SchemaManagementPageComponent,
    SchemaCreateComponent,
    SchemaOverviewComponent,
    FieldOverviewComponent,
    FieldCreationComponent,
    CarReportingPageComponent,
    SchemaCarAssigmentComponent,
    ReportComponent,
    AlertsManagerComponent,
    AlertOverviewComponent,
    AlertCreationComponent,
    UserCreationPageComponent,
    UserOverviewComponent,
    UserCreateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule
  ],
  providers: [
    importProvidersFrom(HttpClientModule),
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
