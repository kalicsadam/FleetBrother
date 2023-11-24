package hu.bme.aut.fleetbrotherserver.controller

import com.fasterxml.jackson.databind.ObjectMapper
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.CarRepository
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.FleetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    @Autowired private val fleetRepository: FleetRepository,
    @Autowired private val carRepository: CarRepository,
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
}