import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { FleetManagementCreateComponent } from '../../components/fleet-management-create/fleet-management-create.component';
import { Fleet } from 'src/app/data/dto/fleet.dto';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';
import { MessageDialogService } from 'src/app/shared/services/message-dialog.service';
import { Router } from '@angular/router';
import { FleetCreationRequestBody } from 'src/app/data/requestbody/fleet-creation.dto';

@Component({
  selector: 'app-fleet-management-page',
  templateUrl: './fleet-management-page.component.html',
  styleUrls: ['./fleet-management-page.component.scss']
})
export class FleetManagementPageComponent implements OnInit {
  fleets : Fleet[] = []

  constructor(
    private _bottomSheet: MatBottomSheet, 
    private fleetManagementService : FleetManagementService, 
    private dialogService : MessageDialogService,
    private routerService : Router){

  }
  ngOnInit(): void {
    this.fetchData();
  }
  
  fetchData(){
    this.fleetManagementService.getAllFleets().subscribe(
      fleets => {this.fleets = fleets}
    )
  }

  openCreatePanel(){
    const ref = this._bottomSheet.open(FleetManagementCreateComponent)
    ref.afterDismissed().subscribe((fleet : FleetCreationRequestBody) => {
      if(fleet != null && fleet.name != null){
        this.fleetManagementService.createFleet(fleet).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Fleet has been created.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }

  onDeletePressed(fleet : Fleet){
    const ref = this.dialogService.openChooseDialog("Delete fleet", `Are you sure you want to delete fleet: #${fleet.id}: ${fleet.name}?`);
    ref.afterClosed().subscribe(decision => {
      if(decision){
        this.fleetManagementService.deleteFleet(fleet.id).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Fleet has been deleted.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }

  onDetailsPressed(fleet : Fleet){
    this.routerService.navigate(["fleet", fleet.id.toString(), "details"])
  }
}
