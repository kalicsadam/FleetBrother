package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.CarDto


interface CarService {
    fun getCarsForFleet(fleetId: Int) : List<CarDto>
    fun getCarById(catId: Int) : CarDto
    fun removeCarFromFleet(carId: Int, fleetId: Int)
}