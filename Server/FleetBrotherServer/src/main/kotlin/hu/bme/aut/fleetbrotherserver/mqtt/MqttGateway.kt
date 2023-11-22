package hu.bme.aut.fleetbrotherserver.mqtt

import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway

/**
 * This is the interface that shall be used for outbound communication.
 * The usage is simple, you only need to request for this bean via
 * the constructor or by Autowiring the property for your class.
 *
 *
 * This way Spring will supply you with the necessary implementation.
 */
@MessagingGateway(name="mqttOutboundGateway")
interface MqttGateway {
    @Gateway(requestChannel = "mqttMeasurementzOutboundChannel")
    fun sendMeasurement(data: String?)

    @Gateway(requestChannel = "mqttLivezOutboundChannel")
    fun sendLive(data: String?)
}