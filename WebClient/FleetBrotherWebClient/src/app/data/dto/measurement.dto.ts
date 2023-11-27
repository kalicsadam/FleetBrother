import { Alert } from "./alert.dto";

export interface Measurement {
    timestamp : Date,
    data : any,
    alerts: Alert[]
}