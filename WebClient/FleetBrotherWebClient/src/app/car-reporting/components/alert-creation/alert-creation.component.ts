import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Field, FieldType } from 'src/app/data/dto/field.dto';
import { Schema } from 'src/app/data/dto/schema.dto';
import { AlertCreationRequestBody } from 'src/app/data/requestbody/alert-creation.dto';

enum AlertType {
  MIN = "min",
  MAX ="max",
  FORBIDDEN = "forbidden",
  EXISTS = "exists"
}

@Component({
  selector: 'app-alert-creation',
  templateUrl: './alert-creation.component.html',
  styleUrls: ['./alert-creation.component.scss']
})
export class AlertCreationComponent {
  @Input() schema : Schema | undefined;
  inputName : string = ""
  inputField : Field | undefined
  inputType : AlertType | undefined = undefined
  inputValue : string = ""

  fieldType = FieldType
  alertType = AlertType

  @Output() onCreate : EventEmitter<AlertCreationRequestBody> = new EventEmitter()

  onSubmit() {
    if(this.validateFields()){
      this.onCreate.emit(this.createReturnValue())
      this.inputName = ""
      this.inputField = undefined
      this.inputType = undefined
      this.inputValue = ""
    }
  }

  createReturnValue(): AlertCreationRequestBody{
    switch(this.inputType){
      case AlertType.MIN:
        return {
          name: this.inputName,
          keyName: this.inputField!.key,
          minValue: Number(this.inputValue)
        }
      case AlertType.MAX:
        return {
          name: this.inputName,
          keyName: this.inputField!.key,
          maxValue: Number(this.inputValue)
        }
      case AlertType.FORBIDDEN:
        return {
          name: this.inputName,
          keyName: this.inputField!.key,
          forbiddenValue: this.inputValue
        }
      case AlertType.EXISTS:
        return {
          name: this.inputName,
          keyName: this.inputField!.key,
          exists: true
        }
      default:
        return null!
    }
  }

  validateFields(){
    if(this.inputName == "" || this.inputField == null || this.inputType == null){
      return false
    }
    if(this.inputType == AlertType.EXISTS){
      return true
    }
    if(this.inputField.type == FieldType.LIST){
      return this.validateField(this.inputValue, this.inputField.elementType!)
    } else {
      return this.validateField(this.inputValue, this.inputField.type)
    }
  }

  validateField(value: string, type: FieldType){
    switch(type){
      case FieldType.NUMBER:
        return Number(value) != Number.NaN
      case FieldType.BOOLEAN:
        return value == "true" || value == "false"
      case FieldType.STRING:
        return typeof value == "string"
      case FieldType.LIST:
      default:
        return false
    }
  }
}
