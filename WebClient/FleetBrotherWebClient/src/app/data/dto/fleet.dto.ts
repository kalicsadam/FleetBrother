import { Car } from "./car.dto"

export interface Fleet {
    id : number,
    name : string,
    description: string
    cars : Car[]
}