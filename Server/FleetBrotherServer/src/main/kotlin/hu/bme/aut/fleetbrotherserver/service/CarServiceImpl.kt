package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.CarRepository
import hu.bme.aut.fleetbrotherserver.data.repositories.FleetRepository
import hu.bme.aut.fleetbrotherserver.dtos.CarDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.CarService
import org.springframework.stereotype.Component

@Component
class CarServiceImpl(
    private val carRepository: CarRepository,
    private val fleetRepository: FleetRepository,
) : CarService {
    override fun getAllCars(): List<CarDto> {
        val cars = carRepository.findAll()
        val carDtos = cars.map { it.convertToDto() }
        return carDtos
    }

    override fun getCarsForFleet(fleetId: Int): List<CarDto> {
        val cars = fleetRepository.getReferenceById(fleetId).cars
        val carDtos = cars.map { it.convertToDto() }

        return carDtos
    }

    override fun getCarById(carId: Int): CarDto {
        val carDto = carRepository.getReferenceById(carId).convertToDto()
        return carDto
    }

    override fun getNewcomerCars(): List<CarDto> {
        val carDtos = carRepository.getNewcomerCars().map { it.convertToDto() }
        return carDtos
    }

    override fun removeCarFromFleet(carId: Int, fleetId: Int) {
        fleetRepository.removeCar(fleetId, carId)
    }

    override fun acceptCarJoinRequest(carId: Int, fleetId: Int) {
        fleetRepository.addCar(fleetId, carId)
    }

    override fun declineCarJoinRequest(carId: Int) {
        val car = carRepository.getReferenceById(carId)
        carRepository.delete(car)
    }
}