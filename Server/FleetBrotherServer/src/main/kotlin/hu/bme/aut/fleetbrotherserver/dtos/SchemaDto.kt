package hu.bme.aut.fleetbrotherserver.dtos

import java.io.Serializable

/**
 * DTO for {@link hu.bme.aut.fleetbrotherserver.data.entities.Schema}
 */
data class SchemaDto(
    val id: Int? = null,
    val name: String,
    val fields: List<FieldDto> = mutableListOf(),
    val measurements: List<MeasurementDto> = mutableListOf(),
    val carIds: List<Int> = mutableListOf(),
) : Serializable