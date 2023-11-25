import { Component, Input } from '@angular/core';
import { CarOverviewMode } from 'src/app/car-management/components/car-overview/car-overview.component';
import { Car } from 'src/app/data/dto/car.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { CarReportingService } from 'src/app/shared/services/car-reporting.service';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';
import { exportExcel } from 'src/app/shared/util/excel.util';

@Component({
  selector: 'app-car-reporting-page',
  templateUrl: './car-reporting-page.component.html',
  styleUrls: ['./car-reporting-page.component.scss']
})
export class CarReportingPageComponent {
  carId : number = 0;
  car : Car | undefined;
  schemas : Schema[] = []

  carOverviewMode = CarOverviewMode

  constructor(
    private carReportingService : CarReportingService,
    private fleetManagementService : FleetManagementService
    ){}

  @Input() set id(carId: string) {
    this.carId = Number(carId);
    this.fetchData()
  }

  fetchData() {
    this.fleetManagementService.getCarById(this.carId).subscribe(car => {
      this.car = car;
    })
    this.carReportingService.getSchemasForCar(Number(this.carId)).subscribe(schemas => {
      this.schemas = schemas
    })
  }

  export(schema : Schema, data : any[]){
    exportExcel(data, schema.name);
  }
}
