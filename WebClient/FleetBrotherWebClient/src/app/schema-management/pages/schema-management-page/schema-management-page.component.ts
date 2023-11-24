import { Component } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { Schema } from 'src/app/data/dto/schema.dto';
import { SchemaManagementService } from 'src/app/shared/services/schema-management.service';
import { SchemaCreateComponent } from '../../components/schema-create/schema-create.component';
import { SchemaCreationRequestBody } from 'src/app/data/requestbody/schema-creation.dto';
import { MessageDialogService } from 'src/app/shared/services/message-dialog.service';
import { FieldCreationRequestBody } from 'src/app/data/requestbody/field-creation.dto';
import { Field } from 'src/app/data/dto/field.dto';
import { SchemaCarAssigmentComponent, SchemaCarAssigmentOutput } from '../../components/schema-car-assigment/schema-car-assigment.component';
import { Car } from 'src/app/data/dto/car.dto';

@Component({
  selector: 'app-schema-management-page',
  templateUrl: './schema-management-page.component.html',
  styleUrls: ['./schema-management-page.component.scss']
})
export class SchemaManagementPageComponent {
  schemas : Schema[] = []

  constructor(
    private schemaService : SchemaManagementService,
    private _bottomSheet: MatBottomSheet,
    private dialogService : MessageDialogService
    ){

  }
  ngOnInit(): void {
    this.fetchData()
  }
  fetchData() {
    this.schemaService.getAllSchemas().subscribe(
      result => {this.schemas = result}
    )
  }

  openCreatePanel(){
    const ref = this._bottomSheet.open(SchemaCreateComponent)
    ref.afterDismissed().subscribe((schema : SchemaCreationRequestBody) => {
      if(schema != null && schema.name != null){
        this.schemaService.createSchema(schema).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Schema has been created.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }

  openAssignPanel(schema : Schema){
    const ref = this._bottomSheet.open(SchemaCarAssigmentComponent, {data: {schema: schema}})
    ref.afterDismissed().subscribe((data : SchemaCarAssigmentOutput) => {
      if(data != null && data.add != null && data.remove != null && data.schema != null){
        this.schemaService.assignSchemaToCar(data.schema.id, data.add.map(cars => cars.id), data.remove.map(cars => cars.id)).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Cars have been assigned to schema.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }

  onAddNewField(schema: Schema, field : FieldCreationRequestBody){
    this.schemaService.addFieldToSchema(schema.id, field).subscribe(result => {
      if(result){
        this.dialogService.openSuccessDialog("Operation successful.", "Field has been added to schema.")
      } else {
        this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
      }
      this.fetchData();
    })
  }

  onDeleteField(schema: Schema, field : Field){
    const ref = this.dialogService.openChooseDialog("Delete field", `Are you sure you want to delete field: ${schema.name}: ${field.key}?`);
    ref.afterClosed().subscribe(decision => {
      if(decision){
        this.schemaService.removeFieldFromSchema(schema.id, field.id).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Field has been removed from schema.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }

  onDeleteSchema(schema: Schema){
    const ref = this.dialogService.openChooseDialog("Delete schema", `Are you sure you want to delete schema: ${schema.id}: ${schema.name}?`);
    ref.afterClosed().subscribe(decision => {
      if(decision){
        this.schemaService.deleteSchema(schema.id).subscribe(result => {
          if(result){
            this.dialogService.openSuccessDialog("Operation successful.", "Schema has been deleted.")
          } else {
            this.dialogService.openErrorDialog("Operation not successful.", "Please try again.")
          }
          this.fetchData();
        })
      }
    })
  }
}
