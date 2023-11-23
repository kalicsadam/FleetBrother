package hu.bme.aut.fleetbrotherserver.mqtt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix="mqtt")
data class MqttParameters (
    val server: String,
    val serverId: String,
    val channels: MqttChannels,
    val userName: String,
    val password: String
)