import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { Car } from 'src/app/data/dto/cat.dto';
import { CarFleetAssigmentComponent } from '../../components/car-fleet-assigment/car-fleet-assigment.component';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';
import { Fleet } from 'src/app/data/dto/fleet.dto';
import { MessageDialogService } from 'src/app/shared/services/message-dialog.service';

@Component({
  selector: 'app-car-assigment-page',
  templateUrl: './car-assigment-page.component.html',
  styleUrls: ['./car-assigment-page.component.scss']
})
export class CarAssigmentPageComponent implements OnInit {
  cars : Car[] = []
  fleets: Fleet[] = []

  constructor(private _bottomSheet : MatBottomSheet, private fleetManagementService : FleetManagementService, private dialogService : MessageDialogService){
      
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
    const ref = this.dialogService.openChooseDialog("Decline car join request", `Are you sure you want to decline the request for: #${car.id}: ${car.name}, License plate: ${car.licenasePlate}, VIN: ${car.vin}`)
    ref.afterClosed().subscribe((decision) => {
      if(decision){
        this.fleetManagementService.declineCarJoinRequest(car.id).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Car has not been added to any fleet.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
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
        if(result){
          this.dialogService.openSuccessDialog("Operation successful.", "Car has been added to fleet.")
        } else {
          this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
        }
        this.fetchData();
      }
    );
  }
}
