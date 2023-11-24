import { Component, Inject, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MAT_BOTTOM_SHEET_DATA, MatBottomSheetRef } from '@angular/material/bottom-sheet';
import { Car } from 'src/app/data/dto/car.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { FleetManagementService } from 'src/app/shared/services/fleet-management.service';

class CheckboxModel {
  car!: Car;
  checked : boolean = false;
}

export interface SchemaCarAssigmentOutput{
    add : Car[]
    remove: Car[]
    schema: Schema
}

@Component({
  selector: 'app-schema-car-assigment',
  templateUrl: './schema-car-assigment.component.html',
  styleUrls: ['./schema-car-assigment.component.scss']
})
export class SchemaCarAssigmentComponent implements OnInit {
  model : CheckboxModel[] = []

  schema : Schema | undefined;

  constructor(
    private fleetManagementService : FleetManagementService,
    private bottomSheetRef : MatBottomSheetRef<SchemaCarAssigmentComponent>,
    @Inject(MAT_BOTTOM_SHEET_DATA) data: {schema: Schema}
  ) {
    this.schema = data.schema
  }

  ngOnInit(): void {
    this.getModel()
  }
  
  getModel(){
    this.fleetManagementService.getAllCars().subscribe(
      cars => {
        this.model = cars.map(car => {
          return {
            car: car,
            checked: this.schema!.carIds.some(carId => carId == car.id)
          }
        })
      }
    );
  }

  onAssign(){
    this.bottomSheetRef.dismiss({
      add: this.model.filter(entry=> entry.checked).map(entry => entry.car),
      remove: this.model.filter(entry=> entry.checked == false).map(entry => entry.car),
      schema: this.schema
    })
  }
}
