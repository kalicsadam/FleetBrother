package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.CarDto


interface CarService {
    fun getAllCars() : List<CarDto>
    fun getCarsForFleet(fleetId: Int) : List<CarDto>
    fun getCarById(carId: Int) : CarDto
    fun getNewcomerCars() : List<CarDto>
    fun removeCarFromFleet(carId: Int, fleetId: Int)
    fun acceptCarJoinRequest(carId: Int, fleetId: Int)
    fun declineCarJoinRequest(carId: Int)
}