package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.UserDto
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService : UserDetailsService {
    fun getUsers() : List<UserDto>
    fun createUser(userDto: UserDto)
    fun deleteUser(userId: Int)
}