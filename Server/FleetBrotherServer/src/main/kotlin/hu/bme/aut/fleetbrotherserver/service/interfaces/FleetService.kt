package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.FleetDto

interface FleetService {
    fun getAllFleets() : List<FleetDto>
    fun getFleetById(fleetId: Int) : FleetDto
    fun createFleet(fleetDto: FleetDto)
    fun deleteFleet(fleetId: Int)
}