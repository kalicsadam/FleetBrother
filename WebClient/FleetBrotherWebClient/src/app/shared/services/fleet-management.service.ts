import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { Car } from 'src/app/data/dto/car.dto';
import { Fleet } from 'src/app/data/dto/fleet.dto';
import { FleetCreationRequestBody } from 'src/app/data/requestbody/fleet-creation.dto';

@Injectable({
  providedIn: 'root'
})
export class FleetManagementService {

  constructor(private http : HttpClient) { }
  

  getAllFleets() : Observable<Fleet[]>{
    return this.http.get<Fleet[]>("/api/fleet")
  }

  getFleetById(fleetId : number) : Observable<Fleet | undefined>{
    return this.http.get<Fleet>("/api/fleet/" + fleetId)
  }

  getAllCars(){
    return this.http.get<Car[]>("/api/car")
  }

  getCarsForFleet(fleetId : number) : Observable<Car[]>{
    return this.http.get<Car[]>("/api/car/getCarsForFleet", {
      params: {
        fleetId: fleetId
      }
    })
  }

  getNewcomerCars() : Observable<Car[]>{
    return this.http.get<Car[]>("/api/car/newcomers")
  }

  getCarById(carId : number) : Observable<Car | undefined>{
    return this.http.get<Car>("/api/car/" + carId)
  }

  createFleet(requestbody: FleetCreationRequestBody) : Observable<boolean> {
    return this.http.put("/api/fleet/create", requestbody, { observe: 'response' }).pipe(map(response => response.ok))
  }

  deleteFleet(fleetId : number) : Observable<boolean> {
    return this.http.delete("/api/fleet/" + fleetId, { observe: 'response' }).pipe(map(response => response.ok))
  }

  removeCarFromFleet(carId : number, fleetId : number) : Observable<boolean> {
    return this.http.delete("/api/car/"+carId+"/removeFromFleet", { observe: 'response', params: {fleetId: fleetId} }).pipe(map(response => response.ok))
  }

  declineCarJoinRequest(carId : number) : Observable<boolean> {
    return this.http.delete("/api/car/" + carId + "/declineRequest", { observe: 'response' }).pipe(map(response => response.ok))
  }

  acceptCarJoinRequest(carId : number, fleetId : number) : Observable<boolean> {
    return this.http.put("/api/car/" + carId + "/acceptRequest", null, { observe: 'response', params: {fleetId: fleetId} }).pipe(map(response => response.ok))
  }
}
