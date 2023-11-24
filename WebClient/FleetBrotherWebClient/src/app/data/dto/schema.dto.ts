import { Car } from "./car.dto";
import { Field } from "./field.dto";

export interface Schema {
    id : number,
    name : string,
    fields : Field[]

    cars : Car[]
    measurements : any[]
}