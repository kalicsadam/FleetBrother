export interface AlertBase {
    id: number,
    name: string,
    keyName: string,
}

export interface MinValueAlert extends AlertBase{
    minValue: number,
}

export interface MaxValueAlert extends AlertBase{
    maxValue: number,
}

export interface ForbiddenValueAlert extends AlertBase{
    forbiddenValue: number | boolean | string,
}

export interface ExistsValueAlert extends AlertBase{
    exists: boolean,
}
export type Alert = MinValueAlert | MaxValueAlert | ForbiddenValueAlert | ExistsValueAlert;