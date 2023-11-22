import { Component } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { FleetManagementCreateComponent } from '../../components/fleet-management-create/fleet-management-create.component';
import { Fleet } from 'src/app/data/dto/fleet.dto';

@Component({
  selector: 'app-fleet-management-page',
  templateUrl: './fleet-management-page.component.html',
  styleUrls: ['./fleet-management-page.component.scss']
})
export class FleetManagementPageComponent {
  fleets : Fleet[] = [
  {
    id: 1,
    cars : [],
    description: "This is a description for my fleet",
    name: "Fleet #1"
  },
  {
    id: 2,
    cars : [],
    description: "This is a description for my fleet",
    name: "Fleet #2"
  },
  {
    id: 3,
    cars : [],
    description: "This is a description for my fleet",
    name: "Fleet #3"
  },
  {
    id: 4,
    cars : [],
    description: "This is a description for my fleet",
    name: "Fleet #4"
  }
]

  constructor(private _bottomSheet: MatBottomSheet){

  }

  openCreatePanel(){
    this._bottomSheet.open(FleetManagementCreateComponent)
  }
}
