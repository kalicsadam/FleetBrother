package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.UserDto

interface UserService {
    fun getUsers() : List<UserDto>
    fun createUser(userDto: UserDto)
    fun deleteUser(userId: Int)
}