package hu.bme.aut.fleetbrotherserver.mqtt

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix="mqtt.channels")
data class MqttChannels(
    val livez: String,
    val measurementz: String,
    val obuConfig: String,
    val remoteProc: String,
    val remoteProcResult: String,
)
