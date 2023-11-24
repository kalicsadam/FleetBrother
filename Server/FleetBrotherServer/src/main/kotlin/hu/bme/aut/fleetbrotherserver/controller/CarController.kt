package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.dtos.CarDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.CarService
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/api/car")
class CarController(
    private val carService: CarService
) {

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Int) : CarDto {
        val carDto = carService.getCarById(id)
        return carDto
    }

    @GetMapping
    fun getCarsForFleet(@RequestParam fleetId: Int) : List<CarDto> {
        val carDtos = carService.getCarsForFleet(fleetId)
        return carDtos
    }

    @GetMapping("/newcomers")
    fun getNewcomerCars() : List<CarDto> {
        val carDtos = carService.getNewcomerCars()
        return carDtos
    }

    @PutMapping("/acceptCar")
    fun acceptCarJoinRequest(@RequestParam carId: Int, @RequestParam fleetId: Int) {
        carService.acceptCarJoinRequest(carId, fleetId)
    }

    @DeleteMapping("/declineCar")
    fun declineCarJoinRequest(@RequestParam carId: Int) {
        carService.declineCarJoinRequest(carId)
    }

    @DeleteMapping
    fun removeCarFromFleet(@RequestParam carId: Int, @RequestParam fleetId: Int) {
        carService.removeCarFromFleet(carId, fleetId)
    }
}