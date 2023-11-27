package hu.bme.aut.fleetbrotherserver.dtos

import java.io.Serializable

/**
 * DTO for {@link hu.bme.aut.fleetbrotherserver.data.entities.Alert}
 */
data class AlertDto(
    val id: Int? = null,
    val name: String,
    val keyName: String,
    val minValue: Double? = null,
    val maxValue: Double? = null,
    val forbiddenValue: String? = null,
    val exists: Boolean? = null,
    val isDeleted: Boolean? = false
) : Serializable