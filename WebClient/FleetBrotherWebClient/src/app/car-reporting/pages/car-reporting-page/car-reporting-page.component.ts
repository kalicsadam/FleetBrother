import { Component, Input } from '@angular/core';
import { Schema } from 'src/app/data/dto/schema.dto';
import { CarReportingService } from 'src/app/shared/services/car-reporting.service';

@Component({
  selector: 'app-car-reporting-page',
  templateUrl: './car-reporting-page.component.html',
  styleUrls: ['./car-reporting-page.component.scss']
})
export class CarReportingPageComponent {
  carId : number = 0;
  schemas : Schema[] = []

  constructor(private carReportingService : CarReportingService){}

  @Input() set id(carId: string) {
    this.carId = Number(carId);
    this.fetchData()
  }

  fetchData() {
    this.carReportingService.getSchemasForCar(Number(this.carId)).subscribe(schemas => {
      this.schemas = schemas
    })
  }
}
