package hu.bme.aut.fleetbrotherserver.dtos

import java.io.Serializable

/**
 * DTO for {@link hu.bme.aut.fleetbrotherserver.data.entities.Car}
 */
data class CarDto(
    val id: Int? = null,
    val name: String? = null,
    val licensePlate: String? = null,
    val vin: String? = null
) : Serializable