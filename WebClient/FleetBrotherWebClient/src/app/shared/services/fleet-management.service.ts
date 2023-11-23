import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Car } from 'src/app/data/dto/cat.dto';
import { Fleet } from 'src/app/data/dto/fleet.dto';

@Injectable({
  providedIn: 'root'
})
export class FleetManagementService {

  constructor() { }

  placeholderCars : Car[] = [
    {
      id: 1,
      licenasePlate: "AAAA-123",
      vin: "placeholder",
      name: "WV Golf mk.3"
    },
    {
      id: 2,
      licenasePlate: "AAAA-234",
      vin: "placeholder",
      name: "WV Golf mk.3"
    },
    {
      id: 3,
      licenasePlate: "AAAA-567",
      vin: "placeholder",
      name: "WV Golf mk.3"
    },
    {
      id: 4,
      licenasePlate: "AAAA-9876",
      vin: "placeholder",
      name: "WV Golf mk.3"
    }
  ]

  placeholderFleets : Fleet[] = [{
    id: 1,
    cars : this.placeholderCars,
    description: "This is a description for my fleet",
    name: "Fleet #1"
  },
  {
    id: 2,
    cars : this.placeholderCars,
    description: "This is a description for my fleet",
    name: "Fleet #2"
  },
  {
    id: 3,
    cars : this.placeholderCars,
    description: "This is a description for my fleet",
    name: "Fleet #3"
  },
  {
    id: 4,
    cars : this.placeholderCars,
    description: "This is a description for my fleet",
    name: "Fleet #4"
  }]

  

  getAllFleets() : Observable<Fleet[]>{
    return new BehaviorSubject(this.placeholderFleets);
  }

  getFleetById(fleetId : number) : Observable<Fleet | undefined>{
    return new BehaviorSubject(this.placeholderFleets.find(fleet => fleet.id == fleetId));
  }

  getCarsForFleet(fleetId : number) : Observable<Car[]>{
    return new BehaviorSubject(this.placeholderCars);
  }

  getNewcomerCars() : Observable<Car[]>{
    return new BehaviorSubject(this.placeholderCars);
  }

  getCarById(carId : number) : Observable<Car | undefined>{
    return new BehaviorSubject(this.placeholderCars.find(car => car.id == carId));
  }

  deleteFleet(fleetId : number) : Observable<boolean> {
    console.log("fleet deleted: " + fleetId)
    return new BehaviorSubject(true);
  }

  removeCarFromFleet(carId : number, fleetId : number) : Observable<boolean> {
    console.log("carId: " + carId + " removed from fleet: " + fleetId)
    return new BehaviorSubject(true);
  }

  declineCarJoinRequest(carId : number) : Observable<boolean> {
    console.log("carId: " + carId + " rejected")
    return new BehaviorSubject(true);
  }

  acceptCarJoinRequest(carId : number, fleetId : number) : Observable<boolean> {
    console.log("carId: " + carId + ", fleetId: " + fleetId + " assigned")
    return new BehaviorSubject(true);
  }
}
