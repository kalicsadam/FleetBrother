package hu.bme.aut.fleetbrotherserver.dtos.security

data class LoginRes(
    var email: String,
    var token: String
)
