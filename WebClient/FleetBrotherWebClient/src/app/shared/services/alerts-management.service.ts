import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Alert } from 'src/app/data/dto/alert.dto';
import { AlertCreationRequestBody } from 'src/app/data/requestbody/alert-creation.dto';

@Injectable({
  providedIn: 'root'
})
export class AlertsManagementService {

  constructor() { }

  placeholderAlerts : Alert[] = [
    {
    id: 1,
    name: "Alert 1",
    keyName: "key",
    minValue: 12
  },
  {
    id: 2,
    name: "Alert 2",
    keyName: "key",
    maxValue: 99
  },
  {
    id: 3,
    name: "Alert 3",
    keyName: "key",
    forbiddenValue: 2.8
  },
  {
    id: 5,
    name: "Alert 4",
    keyName: "key",
    exists: true
  },
]

  getAlerts(carId : number, schemaId : number){
    return new BehaviorSubject(this.placeholderAlerts)
  }

  createAlert(carId : number, schemaId : number, alert : AlertCreationRequestBody){
    this.placeholderAlerts.push({
      ...{id : this.placeholderAlerts[this.placeholderAlerts.length -1].id + 1}, ...(alert)
    });
    return new BehaviorSubject(true)

  }

  deleteAlert(carId : number, schemaId : number, alertId : number){
    this.placeholderAlerts = this.placeholderAlerts.filter(alert => alert.id != alertId)
    return new BehaviorSubject(true)
  }
}
