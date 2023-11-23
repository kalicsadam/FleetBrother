import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { FleetManagementCreateComponent } from '../../components/fleet-management-create/fleet-management-create.component';
import { Fleet } from 'src/app/data/dto/fleet.dto';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';

@Component({
  selector: 'app-fleet-management-page',
  templateUrl: './fleet-management-page.component.html',
  styleUrls: ['./fleet-management-page.component.scss']
})
export class FleetManagementPageComponent implements OnInit {
  fleets : Fleet[] = []

  constructor(private _bottomSheet: MatBottomSheet, private fleetManagementService : FleetManagementService){

  }
  ngOnInit(): void {
    this.fleetManagementService.getAllFleets().subscribe(
      fleets => {this.fleets = fleets}
    )
  }

  openCreatePanel(){
    this._bottomSheet.open(FleetManagementCreateComponent)
  }
}
