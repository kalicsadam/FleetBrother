import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, map } from 'rxjs';
import { Field, FieldType } from 'src/app/data/dto/field.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { FieldCreationRequestBody } from 'src/app/data/requestbody/field-creation.dto';
import { SchemaCreationRequestBody } from 'src/app/data/requestbody/schema-creation.dto';

@Injectable({
  providedIn: 'root'
})
export class SchemaManagementService {
  constructor(private http : HttpClient) { }

  getAllSchemas() : Observable<Schema[]> {
    return this.http.get<Schema[]>("/api/schema")
  }

  createSchema(schema: SchemaCreationRequestBody) {
    return this.http.put("/api/schema/create", schema, { observe: 'response' }).pipe(map(response => response.ok))
  }

  addFieldToSchema(schemaId : number, field: FieldCreationRequestBody) {
    return this.http.put("/api/schema/"+ schemaId + "/addField", field, { observe: 'response' }).pipe(map(response => response.ok))

  }

  removeFieldFromSchema(schemaId : number, fieldId: number) {
    return this.http.delete("/api/schema/"+ schemaId + "/removeField", { observe: 'response', params: {fieldId: fieldId} }).pipe(map(response => response.ok))
  }

  deleteSchema(schemaId : number) {
    return this.http.delete("/api/schema/"+ schemaId, { observe: 'response' }).pipe(map(response => response.ok))
  }

  assignSchemaToCar(schemaId : number, carIdsToAdd : number[], carIdsToRemove : number[]){
    return this.http.put("/api/schema/" + schemaId+ "/assignSchemaToCar", {carIdsToAdd: carIdsToAdd, carIdsToRemove: carIdsToRemove} ,{ observe: 'response' }).pipe(map(response => response.ok))
  }
}
