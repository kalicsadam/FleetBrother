package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.UserRepository
import hu.bme.aut.fleetbrotherserver.dtos.UserDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertBackFromDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.UserService
import org.springframework.stereotype.Component

@Component
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    override fun getUsers(): List<UserDto> {
        val users = userRepository.findAll()
        val userDtos = users.map { it.convertToDto() }
        return userDtos
    }

    override fun createUser(userDto: UserDto) {
        val user = userDto.convertBackFromDto()
        userRepository.save(user)
    }

    override fun deleteUser(userId: Int) {
        userRepository.deleteById(userId)
    }
}