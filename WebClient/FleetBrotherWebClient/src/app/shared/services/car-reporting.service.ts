import { Injectable } from '@angular/core';
import { Schema } from 'src/app/data/dto/schema.dto';

@Injectable({
  providedIn: 'root'
})
export class CarReportingService {

  constructor() { }

  placeholderSchemas : Schema[] = [
    {
      id: 1,
      name: "Schema test 1",
      carIds: [],
      fields: []
    }
  ]

  getSchemasForCar(carId : number){
    return
  }
}
