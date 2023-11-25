package hu.bme.aut.fleetbrotherserver.controller

import com.fasterxml.jackson.databind.ObjectMapper
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.CarRepository
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.FleetRepository
import hu.bme.aut.fleetbrotherserver.service.interfaces.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    @Autowired private val fleetRepository: FleetRepository,
    @Autowired private val carRepository: CarRepository,
    @Autowired private val carService: CarService
) {
    @GetMapping("/demo")
    fun demo() : String {
        val fleet = fleetRepository.getReferenceById(1)
        val car = carRepository.getReferenceById(2)

        fleetRepository.addCar(fleet, car)

        return "str"
    }

    @GetMapping("/demo2")
    fun demo2() : String {
        val fleet = fleetRepository.getReferenceById(1)
        val str = ObjectMapper().writeValueAsString(fleet)
        return str
    }

    @GetMapping("/demo3")
    fun demo3() : String {
        carRepository.removeSchema(2, 1)
        return "demo3"
    }

    @GetMapping("/demo4")
    fun demo4() : String {
        val carDtos = carService.getCarsForFleet(1)

        return carDtos.toString()
    }
}