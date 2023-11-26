package hu.bme.aut.fleetbrotherserver.dtos.mapper

import hu.bme.aut.fleetbrotherserver.data.entities.*
import hu.bme.aut.fleetbrotherserver.dtos.*

fun Car.convertToDto() : CarDto {
    return CarDto(
        id = this.id,
        name = this.name,
        licensePlate = this.licensePlate,
        vin = this.vin,
    )
}

fun Fleet.convertToDto() : FleetDto {
    return FleetDto(
        id = this.id,
        name = this.name,
        description = this.description,
        cars = this.cars.map { it.convertToDto() },
    )
}

fun FleetDto.convertBackFromDto() : Fleet {
    return Fleet(
        id = this.id ?: 0,
        name = this.name,
        description = this.description,
    )
}

fun Field.convertToDto() : FieldDto {
    return FieldDto(
        id = this.id,
        key = this.key,
        elementType = if(this.isList) this.type.toString().lowercase() else null,
        type = if(this.isList) "list" else this.type.toString().lowercase(),
    )
}

fun FieldDto.convertBackFromDto() : Field {
    return Field(
        id = this.id ?: 0,
        key = this.key,
        isList = this.elementType != null,
        type = this.elementType?.let { Type.valueOf(this.elementType.uppercase()) } ?: Type.valueOf(this.type.uppercase())
    )
}

fun Measurement.convertToDto() : MeasurementDto {
    return MeasurementDto(
        id = this.id,
        timestamp = this.timestamp,
        data = this.data,
    )
}

fun Schema.convertToDto() : SchemaDto {
    return SchemaDto(
        id = this.id,
        name = this.name,
        fields = this.fields.map { it.convertToDto() },
        measurements = this.measurements.map { it.convertToDto() },
        carIds = this.schemaCars.map { it.car.id },
    )
}

fun SchemaDto.convertBackFromDto() : Schema {
    return Schema(
        id = this.id ?: 0,
        name = this.name
    )
}

fun Alert.convertToDto() : AlertDto {
    return AlertDto(
        id = this.id,
        name = this.name,
        keyName = this.keyName,
        minValue = this.minValue,
        maxValue = this.maxValue,
        forbiddenValue = this.forbiddenValue,
        exists = this.exists
    )
}

fun AlertDto.convertBackFromDto() : Alert {
    return Alert(
        id = this.id ?: 0,
        name = this.name,
        keyName = this.keyName,
        minValue = this.minValue,
        maxValue = this.maxValue,
        forbiddenValue = this.forbiddenValue,
        exists = this.exists
    )
}