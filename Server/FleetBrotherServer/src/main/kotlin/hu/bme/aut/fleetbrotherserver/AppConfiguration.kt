package hu.bme.aut.fleetbrotherserver

import hu.bme.aut.fleetbrotherserver.mqtt.MqttParameters
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties(MqttParameters::class)
class AppConfiguration


