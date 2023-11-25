package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.FleetRepository
import hu.bme.aut.fleetbrotherserver.dtos.FleetDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertBackFromDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.FleetService
import org.springframework.stereotype.Component

@Component
class FleetServiceImpl (
    private val fleetRepository: FleetRepository
) : FleetService {
    override fun getAllFleets(): List<FleetDto> {
        val fleets = fleetRepository.findAll()
        val fleetDtos = fleets.map { it.convertToDto() }

        return fleetDtos
    }

    override fun getFleetById(fleetId: Int) : FleetDto {
        val fleet = fleetRepository.getReferenceById(fleetId)
        val fleetDto = fleet.convertToDto()

        return fleetDto
    }

    override fun createFleet(fleetDto: FleetDto) {
        val fleet = fleetDto.convertBackFromDto()
        fleetRepository.save(fleet)
    }

    override fun deleteFleet(fleetId: Int) {
        fleetRepository.deleteById(fleetId)
    }
}