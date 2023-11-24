package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.CarRepository
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.FleetRepository
import hu.bme.aut.fleetbrotherserver.dtos.CarDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.Converter.Companion.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.CarService
import org.springframework.stereotype.Component

@Component
class CarServiceImpl(
    private val carRepository: CarRepository,
    private val fleetRepository: FleetRepository,
) : CarService {
    override fun getCarsForFleet(fleetId: Int): List<CarDto> {
        val cars = fleetRepository.getReferenceById(fleetId).cars
        val carDtos = cars.map { it.convertToDto() }

        return carDtos
    }

    override fun getCarById(carId: Int): CarDto {
        val carDto = carRepository.getReferenceById(carId).convertToDto()

        return carDto
    }

    override fun removeCarFromFleet(carId: Int, fleetId: Int) {
        fleetRepository.removeCar(fleetId, carId)
    }
}