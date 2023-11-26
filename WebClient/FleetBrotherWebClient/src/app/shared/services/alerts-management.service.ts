import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map } from 'rxjs';
import { Alert } from 'src/app/data/dto/alert.dto';
import { AlertCreationRequestBody } from 'src/app/data/requestbody/alert-creation.dto';

@Injectable({
  providedIn: 'root'
})
export class AlertsManagementService {

  constructor(private http: HttpClient) { }
  
  getAlerts(carId : number){
    return this.http.get<Alert[]>("/api/alert", {params: {carId: carId}})
  }

  createAlert(carId : number, alert : AlertCreationRequestBody){
    return this.http.put("/api/alert", alert, { params: {carId: carId} ,observe: 'response' }).pipe(map(response => response.ok))

  }

  deleteAlert(alertId : number){
    return this.http.delete("/api/alert/" + alertId, { observe: 'response' }).pipe(map(response => response.ok))
  }
}
