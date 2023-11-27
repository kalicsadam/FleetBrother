package hu.bme.aut.fleetbrotherserver.mail

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix="mail")
data class MailParameters(
        val host: String,
        val port: String,
        val from: String,
        val to: String
)
