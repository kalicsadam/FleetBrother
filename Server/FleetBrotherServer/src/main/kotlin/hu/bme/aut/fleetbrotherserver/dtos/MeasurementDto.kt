package hu.bme.aut.fleetbrotherserver.dtos

import java.io.Serializable
import java.sql.Timestamp

/**
 * DTO for {@link hu.bme.aut.fleetbrotherserver.data.entities.Measurement}
 */
data class MeasurementDto(
    val id: Int,
    val timestamp: Timestamp,
    val data: String,
) : Serializable