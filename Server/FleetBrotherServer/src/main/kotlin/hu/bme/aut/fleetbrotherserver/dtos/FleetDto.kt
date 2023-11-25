package hu.bme.aut.fleetbrotherserver.dtos

import java.io.Serializable

/**
 * DTO for {@link hu.bme.aut.fleetbrotherserver.data.entities.Fleet}
 */
data class FleetDto(
    val id: Int,
    val name: String,
    val description: String,
    val cars: List<CarDto> = mutableListOf()
) : Serializable