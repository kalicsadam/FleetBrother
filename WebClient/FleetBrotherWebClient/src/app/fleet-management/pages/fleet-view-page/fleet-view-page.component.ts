import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { CarOverviewMode } from 'src/app/car-management/components/car-overview/car-overview.component';
import { Car } from 'src/app/data/dto/car.dto';
import { Fleet } from 'src/app/data/dto/fleet.dto';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';
import { MessageDialogService } from 'src/app/shared/services/message-dialog.service';

@Component({
  selector: 'app-fleet-view-page',
  templateUrl: './fleet-view-page.component.html',
  styleUrls: ['./fleet-view-page.component.scss']
})
export class FleetViewPageComponent{
  fleetId: string = "";
  fleet : Fleet | undefined;

  carOverviewModes = CarOverviewMode;

  constructor(
    private fleetManagementService : FleetManagementService,
    private dialogService : MessageDialogService
    ){

  }
  @Input() set id(fleetId: string) {
    this.fleetId = fleetId;
    this.fetchData()
  }

  fetchData(){
    this.fleetManagementService.getFleetById(Number(this.fleetId)).subscribe(fleet => {this.fleet = fleet})
  }

  onDeletePressed(car : Car){
    const ref = this.dialogService.openChooseDialog("Remove car from fleet", `Are you sure you want remove car (#${car.id}: ${car.name}, License plate: ${car.licensePlate}, VIN: ${car.vin}) from fleet (#${this.fleet?.id}: ${this.fleet?.name})?`)
    ref.afterClosed().subscribe(decision => {
      if(decision){
        this.fleetManagementService.removeCarFromFleet(car.id, this.fleet?.id ?? 0).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Car has been removed from fleet.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData()
        })
      }
    })
  }
}
