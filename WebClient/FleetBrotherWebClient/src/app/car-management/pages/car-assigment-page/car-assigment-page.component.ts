import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { Car } from 'src/app/data/dto/cat.dto';
import { CarFleetAssigmentComponent } from '../../components/car-fleet-assigment/car-fleet-assigment.component';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';
import { Fleet } from 'src/app/data/dto/fleet.dto';

@Component({
  selector: 'app-car-assigment-page',
  templateUrl: './car-assigment-page.component.html',
  styleUrls: ['./car-assigment-page.component.scss']
})
export class CarAssigmentPageComponent implements OnInit {
  cars : Car[] = []
  fleets: Fleet[] = []

  constructor(private _bottomSheet : MatBottomSheet, private fleetManagementService : FleetManagementService){
      
  }
  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(){
    this.fleetManagementService.getNewcomerCars().subscribe(cars=> this.cars = cars)
    this.fleetManagementService.getAllFleets().subscribe(
      fleets => {this.fleets = fleets}
    )
  }

  onAccept(car : Car){
    const ref = this._bottomSheet.open(CarFleetAssigmentComponent, {data: { car: car, fleets: this.fleets }})
    ref.afterDismissed().subscribe(result => this.onAcceptedHandle(result, car))
  }

  onDecline(car : Car){

  }

  canAssign(){
    return this.fleets.length > 0;
  }

  onAcceptedHandle(selectedFleet : Fleet | undefined, car : Car){
    if(selectedFleet == null){
      return;
    }

    this.fleetManagementService.acceptCarJoinRequest(car.id, selectedFleet.id).subscribe(
      result => {
        if(!result){
          //TODO raise error
        }
        this.fetchData();
      }
    );
  }
}
