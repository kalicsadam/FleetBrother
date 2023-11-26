package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.dtos.AlertDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.AlertService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/alert")
class AlertController(
    private val alertService: AlertService
) {
    @GetMapping
    fun getAlerts(@RequestParam carId: Int) : List<AlertDto> {
        return alertService.getAlerts(carId)
    }

    @PutMapping
    fun createAlert(@RequestParam carId: Int, @RequestBody alertDto: AlertDto) {
        alertService.createAlert(carId, alertDto)
    }

    @DeleteMapping("/{alertId}")
    fun deleteAlert(@PathVariable alertId: Int) {
        alertService.deleteAlert(alertId)
    }
}