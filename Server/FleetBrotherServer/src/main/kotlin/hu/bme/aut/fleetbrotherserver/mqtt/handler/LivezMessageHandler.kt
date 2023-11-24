package hu.bme.aut.fleetbrotherserver.mqtt.handler

import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler

class LivezMessageHandler : MessageHandler {
    private val logger = LoggerFactory.getLogger(LivezMessageHandler::class.java)!!

    override fun handleMessage(message: Message<*>) {
        // Kéne sztem a Car-nak egy is lastLive timestamp,
        // meg egy isLive computed bit oszlop
        logger.info("Livez: heartbeat signal received.")
    }
}