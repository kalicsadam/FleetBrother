package hu.bme.aut.fleetbrotherserver

import hu.bme.aut.fleetbrotherserver.mqtt.MqttConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties(MqttConfiguration::class)
class AppConfiguration


