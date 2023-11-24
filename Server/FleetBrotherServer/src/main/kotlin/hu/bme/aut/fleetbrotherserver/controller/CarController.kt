package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.dtos.CarDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.CarService
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/api/car")
class CarController(
    private val carService: CarService
) {

    @GetMapping("/{carId}")
    fun getCarById(@PathVariable carId: Int) : CarDto {
        val carDto = carService.getCarById(carId)
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

    @PutMapping("/{carId}/acceptRequest")
    fun acceptCarJoinRequest(@PathVariable carId: Int, @RequestParam fleetId: Int) {
        carService.acceptCarJoinRequest(carId, fleetId)
    }

    @DeleteMapping("/{carId}/declineRequest")
    fun declineCarJoinRequest(@PathVariable carId: Int) {
        carService.declineCarJoinRequest(carId)
    }

    @DeleteMapping("/{carId}/removeFromFiled")
    fun removeCarFromFleet(@PathVariable carId: Int, @RequestParam fleetId: Int) {
        carService.removeCarFromFleet(carId, fleetId)
    }
}