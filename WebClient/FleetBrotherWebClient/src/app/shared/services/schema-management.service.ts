import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Field, FieldType } from 'src/app/data/dto/field.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { FieldCreationRequestBody } from 'src/app/data/requestbody/field-creation.dto';
import { SchemaCreationRequestBody } from 'src/app/data/requestbody/schema-creation.dto';

@Injectable({
  providedIn: 'root'
})
export class SchemaManagementService {
  constructor() { }

  placeholderFields : Field[] = [
    {
      id: 1,
      key: "boolean",
      elementType: null,
      type: FieldType.BOOLEAN
    },
    {
      id: 2,
      key: "number",
      elementType: null,
      type: FieldType.NUMBER
    },
    {
      id: 3,
      key: "string",
      elementType: null,
      type: FieldType.STRING
    },
    {
      id: 4,
      key: "list",
      elementType:  FieldType.NUMBER,
      type: FieldType.LIST
    },
  ]

  placeholderSchema : Schema[] = [
    {
      id: 1,
      fields: this.placeholderFields,
      name: "Schema #1",
      cars: [],
      measurements: []
    },
    {
      id: 2,
      fields: this.placeholderFields,
      name: "Schema #2",
      cars: [],
      measurements: []
    },
    {
      id: 3,
      fields: this.placeholderFields,
      name: "Schema #3",
      cars: [],
      measurements: []
    }
  ]

  getAllSchemas() : Observable<Schema[]> {
    return new BehaviorSubject(this.placeholderSchema)
  }

  createSchema(schema: SchemaCreationRequestBody) {
    this.placeholderSchema.push({
      id : this.placeholderSchema[this.placeholderSchema.length - 1].id + 1,
      cars: [],
      measurements: [],
      fields: [],
      name: schema.name
    })
    return new BehaviorSubject(true);
  }

  addFieldToSchema(schemaId : number, field: FieldCreationRequestBody) {
    const schema = this.placeholderSchema.find(schema => schemaId == schema.id);
    if(schema == null){
      return new BehaviorSubject(false);
    }
    schema.fields.push({
      id: schema.fields[schema.fields.length - 1].id + 1,
      key: field.key,
      type: field.type,
      elementType: field.elementType
    })
    return new BehaviorSubject(true);
  }

  removeFieldFromSchema(schemaId : number, fieldId: number) {
    const schema = this.placeholderSchema.find(schema => schemaId == schema.id);
    if(schema == null){
      return new BehaviorSubject(false);
    }
    schema.fields = schema.fields.filter(field => field.id != fieldId);
    return new BehaviorSubject(true);
  }
}
