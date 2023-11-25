export interface AlertCreationRequestBodyBase {
    name: string,
    keyName: string,
}

export interface MinValueAlertCreationRequestBody extends AlertCreationRequestBodyBase{
    minValue: number,
}

export interface MaxValueAlertCreationRequestBody extends AlertCreationRequestBodyBase{
    maxValue: number,
}

export interface ForbiddenValueAlertCreationRequestBody extends AlertCreationRequestBodyBase{
    forbiddenValue: string,
}

export interface ExistsValueAlertCreationRequestBody extends AlertCreationRequestBodyBase{
    exists: boolean,
}

export type AlertCreationRequestBody = MinValueAlertCreationRequestBody | MaxValueAlertCreationRequestBody | ForbiddenValueAlertCreationRequestBody | ExistsValueAlertCreationRequestBody;