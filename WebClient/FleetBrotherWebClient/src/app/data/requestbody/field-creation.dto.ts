import { FieldType } from "../dto/field.dto";

export interface FieldCreationRequestBody {
    type : FieldType,
    elementType : FieldType,
    key : string
}