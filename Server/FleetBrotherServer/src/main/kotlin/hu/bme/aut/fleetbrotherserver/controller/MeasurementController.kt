package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.dtos.MeasurementDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.MeasurementService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/measurement")
class MeasurementController(
    private val measurementService: MeasurementService
) {
    @GetMapping
    fun getMeasurements(@RequestParam carId: Int, @RequestParam schemaId: Int) : List<MeasurementDto> {
        return measurementService.getMeasurements(carId, schemaId)
    }
}