import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { FieldType } from 'src/app/data/dto/field.dto';
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
      fields: [{
        id: 1,
        elementType: null,
        key: "date",
        type: FieldType.STRING
      },
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
      fields: [{
        id: 1,
        elementType: null,
        key: "date",
        type: FieldType.STRING
      },
      {
        id: 2,
        elementType: null,
        key: "numberField",
        type: FieldType.NUMBER
      }]
    }
  ]

  placeholderMeasurements1 = [
    {
      date: "2023-01-01T09:00:00.0000Z",
      booleanField: true
    },
    {
      date: "2023-01-01T10:00:00.0000Z",
      booleanField: false
    },
    {
      date: "2023-01-01T11:00:00.0000Z",
      booleanField: true
    }
  ]

  placeholderMeasurements2 = [
    {
      date: "2023-01-01T09:00:00.0000Z",
      numberField: 1
    },
    {
      date: "2023-01-01T10:00:00.0000Z",
      numberField: 0.23
    },
    {
      date: "2023-01-01T11:00:00.0000Z",
      numberField: 45654.1223
    }
  ]

  getSchemasForCar(carId : number){
    return new BehaviorSubject(this.placeholderSchemas)
  }

  getMeasurements(carId : number, schemaId : number) : Observable<any[]>{
    if(schemaId == 1){
      return new BehaviorSubject(this.placeholderMeasurements1)
    } else {
      return new BehaviorSubject(this.placeholderMeasurements2)
    }
    
  }
}
