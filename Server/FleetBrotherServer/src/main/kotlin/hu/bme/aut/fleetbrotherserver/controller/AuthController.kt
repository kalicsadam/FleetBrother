package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.auth.JwtUtil
import hu.bme.aut.fleetbrotherserver.data.repositories.UserRepository
import hu.bme.aut.fleetbrotherserver.dtos.security.ErrorRes
import hu.bme.aut.fleetbrotherserver.dtos.security.LoginReq
import hu.bme.aut.fleetbrotherserver.dtos.security.LoginRes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository,
) {

    @ResponseBody
    @PostMapping("/login")
    fun login(@RequestBody loginReq: LoginReq): ResponseEntity<*>? {
        return try {
            val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginReq.email,
                    loginReq.password
                )
            )

            val email: String = authentication.name
            val user = userRepository.findUserByEmail(email)
            val token = jwtUtil.createToken(user)
            val loginRes = LoginRes(email, token)

            ResponseEntity.ok<LoginRes>(loginRes)
        } catch (e: BadCredentialsException) {
            val errorResponse = ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ErrorRes>(errorResponse)
        } catch (e: Exception) {
            val errorResponse = ErrorRes(HttpStatus.BAD_REQUEST, e.message!!)
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ErrorRes>(errorResponse)
        }
    }

    @PostMapping("/checkToken")
    fun checkToken() : ResponseEntity<String> {
        return ResponseEntity.ok().body("")
    }
}