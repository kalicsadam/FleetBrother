import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { FieldType } from 'src/app/data/dto/field.dto';
import { Measurement } from 'src/app/data/dto/measurement.dto';
import { Schema } from 'src/app/data/dto/schema.dto';

@Injectable({
  providedIn: 'root'
})
export class CarReportingService {

  constructor(private http: HttpClient) { }

  getSchemasForCar(carId: number) {
    return this.http.get<Schema[]>("/api/schema/getSchemasForCar", { params: { carId: carId } })
  }

  getMeasurements(carId: number, schemaId: number): Observable<Measurement[]> {
    return this.http.get<{ timestamp: string, data: string }[]>("/api/measurement", { params: { carId: carId, schemaId: schemaId } })
      .pipe(map(result => {
        return result.map(m => {
          return {
            timestamp: new Date(m.timestamp),
            data: JSON.parse(m.data)
          }
        })
      }))
  }
}
