package hu.bme.aut.fleetbrotherserver.firebase

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix="firebase")
data class FirebaseParameters(
        val credentialsFile: String,
        val alertTopic: String
)
