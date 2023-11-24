export enum FieldType {
    NUMBER = "number",
    STRING = "string",
    BOOLEAN = "boolean",
    LIST = "list"
}

export interface Field {
    id : number,
    type : FieldType,
    elementType : FieldType | null,
    key : string
}