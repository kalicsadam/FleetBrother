import { Component, EventEmitter, Output } from '@angular/core';
import { FieldType } from 'src/app/data/dto/field.dto';
import { FieldCreationRequestBody } from 'src/app/data/requestbody/field-creation.dto';

@Component({
  selector: 'app-field-creation',
  templateUrl: './field-creation.component.html',
  styleUrls: ['./field-creation.component.scss']
})
export class FieldCreationComponent {
  key : string = "";
  type : string = "";
  elementType : string | null = null;

  @Output() onAddNewField : EventEmitter<FieldCreationRequestBody> = new EventEmitter();

  fireOnAddNewField(){
    if(this.key == "" || this.type == "" || (this.type == "list" && this.elementType == "")){
      return;
    }
    this.onAddNewField.emit({
      key: this.key,
      type: <FieldType> this.type,
      elementType: <FieldType> this.elementType
    })

    this.key= "";
    this.type = "";
    this.elementType = null;
  }
}
