package hu.bme.aut.fleetbrotherserver.dtos.mapper

import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Fleet
import hu.bme.aut.fleetbrotherserver.dtos.CarDto
import hu.bme.aut.fleetbrotherserver.dtos.FleetDto

class Converter {
    companion object {
        fun Car.convertToDto() : CarDto {
            return CarDto(
                id = this.id,
                name = this.name,
                licensePlate = this.licensePlate,
                vin = this.vin
            )
        }

        fun Fleet.convertToDto() : FleetDto {
            return FleetDto(
                id = this.id,
                name = this.name,
                description = this.description,
                cars = this.cars.map { it.convertToDto() }
            )
        }

        fun FleetDto.convertBackFromDto() : Fleet {
            return Fleet(
                id = this.id,
                name = this.name,
                description = this.description
            )
        }
    }
}