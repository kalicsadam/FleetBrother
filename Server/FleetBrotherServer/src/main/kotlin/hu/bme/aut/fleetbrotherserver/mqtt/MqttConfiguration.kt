package hu.bme.aut.fleetbrotherserver.mqtt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix="mqtt")
data class MqttConfiguration (
    val server: String,
    val livez: String,
    val measurementz: String,
    val serverId: String,
    val userName: String,
    val password: String
)