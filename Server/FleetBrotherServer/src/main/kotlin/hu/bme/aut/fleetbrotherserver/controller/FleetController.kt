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

    @GetMapping("/{fleetId}")
    fun getFleetById(@PathVariable fleetId: Int) : FleetDto {
        return fleetService.getFleetById(fleetId)
    }

    @PutMapping("/create")
    fun createFleet(@RequestBody fleetDto: FleetDto) {
        fleetService.createFleet(fleetDto)
    }

    @DeleteMapping("/{fleetId}")
    fun deleteFleet(@PathVariable fleetId: Int) {
        fleetService.deleteFleet(fleetId)
    }
}