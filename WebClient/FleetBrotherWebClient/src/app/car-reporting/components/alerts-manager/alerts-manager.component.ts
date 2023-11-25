import { Component, Inject, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Alert } from 'src/app/data/dto/alert.dto';
import { Car } from 'src/app/data/dto/car.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { AlertCreationRequestBody } from 'src/app/data/requestbody/alert-creation.dto';
import { AlertsManagementService } from 'src/app/shared/services/alerts-management.service';
import { MessageDialogService } from 'src/app/shared/services/message-dialog.service';

@Component({
  selector: 'app-alerts-manager',
  templateUrl: './alerts-manager.component.html',
  styleUrls: ['./alerts-manager.component.scss']
})
export class AlertsManagerComponent implements OnInit {
  constructor(
    private alertService : AlertsManagementService,
    private dialogService : MessageDialogService,
    @Inject(MAT_DIALOG_DATA) public data: {car: Car, schema: Schema}
  ){
    this.car = data.car,
    this.schema = data.schema;
  }

  ngOnInit(): void {
    this.fetchData()
  }

  car :Car
  schema : Schema

  alerts : Alert[] = []

  fetchData(){
    this.alertService.getAlerts(this.car.id, this.schema.id).subscribe(alerts => {
      this.alerts = alerts
    })
  }

  onCreate(alert : AlertCreationRequestBody){
    this.alertService.createAlert(this.car.id, this.schema.id, alert).subscribe(result => {
      if(result){
        this.dialogService.openSuccessDialog("Operation successful.", "Alert has been created.")
      } else {
        this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
      }
      this.fetchData();
    })
  }

  onDelete(alert: Alert) {
    const ref = this.dialogService.openChooseDialog("Delete alert for car", `Are you sure you want to delete alert: #${alert.id}: ${alert.name} for #${this.car.id}: ${this.car.name}, License plate: ${this.car.licenasePlate}, VIN: ${this.car.vin}?`)
    ref.afterClosed().subscribe((decision) => {
      if(decision){
        this.alertService.deleteAlert(this.car.id, this.schema.id, alert.id).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Alert has been deleted.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
    
  }
}
