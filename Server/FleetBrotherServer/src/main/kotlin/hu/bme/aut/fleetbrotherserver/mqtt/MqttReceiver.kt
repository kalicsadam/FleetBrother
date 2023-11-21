package hu.bme.aut.fleetbrotherserver.mqtt

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class MqttReceiver : MqttCallback {
    val logger = LoggerFactory.getLogger(MqttReceiver::class.java)!!

    override fun connectionLost(cause: Throwable?) {
        logger.info("Connection Lost: ${cause?.cause}")
    }

    override fun messageArrived(topic: String?, message: MqttMessage?) {
        logger.info("Message Arrived: ${message?.id}")
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
        logger.info("Message Delivery Complete ${token?.messageId}")
    }
}