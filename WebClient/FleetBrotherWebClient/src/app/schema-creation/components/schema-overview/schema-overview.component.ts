import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Field } from 'src/app/data/dto/field.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { FieldCreationRequestBody } from 'src/app/data/requestbody/field-creation.dto';

@Component({
  selector: 'app-schema-overview',
  templateUrl: './schema-overview.component.html',
  styleUrls: ['./schema-overview.component.scss']
})
export class SchemaOverviewComponent {
  @Input() schema : Schema | undefined;

  @Output() onAddNewField : EventEmitter<FieldCreationRequestBody> = new EventEmitter();
  @Output() onDeleteField : EventEmitter<Field> = new EventEmitter();
  @Output() onDeleteSchema : EventEmitter<Schema> = new EventEmitter();

  onAddNewFieldFired(rb : FieldCreationRequestBody){
    this.onAddNewField.emit(rb)
  }

  onDeleteFieldFired(field : Field){
    this.onDeleteField.emit(field);
  }

  onDeleteSchemaFired(schema : Schema){
    this.onDeleteSchema.emit(schema);
  }
}
