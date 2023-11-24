package hu.bme.aut.fleetbrotherserver.mqtt

//import org.springframework.integration.annotation.*
//import org.springframework.integration.mqtt.support.MqttHeaders

/**
 * This is the interface that shall be used for outbound communication.
 * The usage is simple, you only need to request for this bean via
 * the constructor or by Autowiring the property for your class.
 *
 *
 * This way Spring will supply you with the necessary implementation.
 */
//@MessagingGateway(name="mqttOutboundGateway")
//interface MqttGateway {
//    @Gateway(
//            requestChannel = "mqttOutboundChannel",
//            headers = [GatewayHeader(
//                    name = MqttHeaders.TOPIC,
//                    expression = "@'mqtt.channels-hu.bme.aut.fleetbrotherserver.mqtt.MqttChannels'.remoteProc"
//            )]
//    )
//    fun sendRemoteProcedureCall(data: String?)
//
//    @Gateway(
//            requestChannel = "mqttOutboundChannel",
//            headers = [GatewayHeader(
//                    name = MqttHeaders.TOPIC,
//                    expression = "@'mqtt.channels-hu.bme.aut.fleetbrotherserver.mqtt.MqttChannels'.obuConfig"
//            )]
//    )
//    fun sendConfig(data: String?)
//}