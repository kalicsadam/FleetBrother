package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.UserRepository
import hu.bme.aut.fleetbrotherserver.dtos.UserDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertBackFromDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.UserService
import org.springframework.security.core.userdetails.UserDetails
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
        userRepository.saveUserWithHashing(user)
    }

    override fun deleteUser(userId: Int) {
        userRepository.deleteById(userId)
    }

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findUserByEmail(email)
        val userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(user.email)
            .password(user.passwordHash)
            .roles(user.role)
            .build()

        return userDetails
    }
}