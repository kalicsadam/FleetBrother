package hu.bme.aut.fleetbrotherserver.dtos

import java.io.Serializable

/**
 * DTO for {@link hu.bme.aut.fleetbrotherserver.data.entities.Field}
 */
data class FieldDto(
    val id: Int? = null,
    val key: String,
    val elementType: String?,
    val type: String,
) : Serializable