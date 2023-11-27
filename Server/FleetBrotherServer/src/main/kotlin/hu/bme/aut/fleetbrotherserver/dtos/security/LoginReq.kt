package hu.bme.aut.fleetbrotherserver.dtos.security

data class LoginReq(
    var email: String,
    var password: String,
)