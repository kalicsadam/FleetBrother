import { Component, Inject, Input, OnInit } from '@angular/core';
import { MAT_BOTTOM_SHEET_DATA, MatBottomSheet } from '@angular/material/bottom-sheet';
import { Car } from 'src/app/data/dto/cat.dto';
import { Fleet } from 'src/app/data/dto/fleet.dto';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';

@Component({
  selector: 'app-car-fleet-assigment',
  templateUrl: './car-fleet-assigment.component.html',
  styleUrls: ['./car-fleet-assigment.component.scss']
})
export class CarFleetAssigmentComponent implements OnInit {
  @Input() car : Car | undefined = undefined;
  @Input() fleets : Fleet[] = [];

  selectedFleet : Fleet | undefined;

  constructor(@Inject(MAT_BOTTOM_SHEET_DATA) data: {car: Car, fleets : Fleet[]}, private _bottomSheet : MatBottomSheet){
    this.car = data.car;
    this.fleets = data.fleets;
  }
  ngOnInit(): void {
    
  }

  onAssign(){
    this._bottomSheet.dismiss(this.selectedFleet);
  }
}
