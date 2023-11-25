import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { FieldType } from 'src/app/data/dto/field.dto';
import { Measurement } from 'src/app/data/dto/measurement.dto';
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
      fields: [
      {
        id: 2,
        elementType: null,
        key: "booleanField",
        type: FieldType.BOOLEAN
      }]
    },
    {
      id: 2,
      name: "Schema test 2",
      carIds: [],
      fields: [
      {
        id: 2,
        elementType: null,
        key: "numberField",
        type: FieldType.NUMBER
      }]
    }
  ]

  placeholderMeasurements1 : Measurement[] = [
    {
      timestamp: new Date(2023, 1, 1, 9, 0, 0, 0),
      data: {
        booleanField: true
      }
    },
    {
      timestamp: new Date(2023, 1, 1, 10, 0, 0, 0),
      data: {
        booleanField: false
      }
    },
    {
      timestamp: new Date(2023, 1, 1, 11, 0, 0, 0),
      data: {
        booleanField: false
      }
    },
  ]

  placeholderMeasurements2 : Measurement[] = [
    {
      timestamp: new Date(2023, 1, 1, 9, 0, 0, 0),
      data: {
        numberField: 123
      }
    },
    {
      timestamp: new Date(2023, 1, 1, 10, 0, 0, 0),
      data: {
        numberField: 0.23
      }
    },
    {
      timestamp: new Date(2023, 1, 1, 11, 0, 0, 0),
      data: {
        numberField: 4568.156
      }
    },
  ]

  getSchemasForCar(carId : number){
    return new BehaviorSubject(this.placeholderSchemas)
  }

  getMeasurements(carId : number, schemaId : number){
    if(schemaId == 1){
      return new BehaviorSubject(this.placeholderMeasurements1)
    } else {
      return new BehaviorSubject(this.placeholderMeasurements2)
    }
    
  }
}
