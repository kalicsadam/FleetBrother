import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-car-reporting-page',
  templateUrl: './car-reporting-page.component.html',
  styleUrls: ['./car-reporting-page.component.scss']
})
export class CarReportingPageComponent {
  carId : string = "";

  @Input() set id(carId: string) {
    this.carId = carId;
    this.fetchData()
  }

  fetchData() {
    throw new Error('Method not implemented.');
  }
}
