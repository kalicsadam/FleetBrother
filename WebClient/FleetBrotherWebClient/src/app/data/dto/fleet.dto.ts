import { Car } from "./cat.dto"

export interface Fleet {
    id : number,
    name : string,
    description: string
    cars : Car[]
}