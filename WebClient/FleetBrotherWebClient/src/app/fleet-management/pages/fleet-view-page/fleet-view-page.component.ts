import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { CarOverviewMode } from 'src/app/car-management/components/car-overview/car-overview.component';
import { Fleet } from 'src/app/data/dto/fleet.dto';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';

@Component({
  selector: 'app-fleet-view-page',
  templateUrl: './fleet-view-page.component.html',
  styleUrls: ['./fleet-view-page.component.scss']
})
export class FleetViewPageComponent{
  fleet : Fleet | undefined; 

  carOverviewModes = CarOverviewMode;

  constructor(private fleetManagementService : FleetManagementService){

  }
  @Input() set id(fleetId: string) {
    this.fleetManagementService.getFleetById(Number(fleetId)).subscribe(fleet => {this.fleet = fleet})
  }
}
