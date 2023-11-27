package hu.bme.aut.fleetbrotherserver.dtos.security

import org.springframework.http.HttpStatus

data class ErrorRes(
    var httpStatus: HttpStatus,
    var message: String
)
