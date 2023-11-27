package hu.bme.aut.fleetbrotherserver.dtos

import java.io.Serializable

/**
 * DTO for {@link hu.bme.aut.fleetbrotherserver.data.entities.User}
 */
data class UserDto(
    val id: Int? = null,
    val email: String? = null,
    val password: String? = null,
    val isAdmin: Boolean? = null
) : Serializable