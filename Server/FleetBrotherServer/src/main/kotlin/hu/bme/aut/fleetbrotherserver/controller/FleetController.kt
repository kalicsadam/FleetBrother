package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.dtos.FleetDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.FleetService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/fleet")
class FleetController(
    private val fleetService: FleetService
) {
    @GetMapping
    fun getAllFleets() : List<FleetDto> {
        return fleetService.getAllFleets()
    }

    @GetMapping("/{id}")
    fun getFleetById(@PathVariable id: Int) : FleetDto {
        return fleetService.getFleetById(id)
    }

    @PutMapping
    fun createFleet(@RequestBody fleetDto: FleetDto) {
        fleetService.createFleet(fleetDto)
    }

    @DeleteMapping
    fun deleteFleet(@RequestParam id: Int) {
        fleetService.deleteFleet(id)
    }
}