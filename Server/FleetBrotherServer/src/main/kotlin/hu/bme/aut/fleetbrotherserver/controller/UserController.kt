package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.dtos.UserDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUsers() : List<UserDto> {
        return userService.getUsers()
    }

    @PutMapping
    fun createUser(@RequestBody userDto: UserDto) {

        userService.createUser(userDto)
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Int) {
        userService.deleteUser(userId)
    }
}