package hu.bme.aut.fleetbrotherserver.mqtt.handler

import com.fasterxml.jackson.databind.ObjectMapper
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.CarRepository
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

class LivezMessageHandler(private val carRepo: CarRepository) : MessageHandler {
    private val logger = LoggerFactory.getLogger(LivezMessageHandler::class.java)!!

    override fun handleMessage(message: Message<*>) {
        val messageRoot = ObjectMapper().readTree(message.payload.toString())

        val carId = messageRoot["id"].asInt()
        val carOpt = carRepo.findById(carId)

        if(carOpt.isEmpty) {
            return
        }

        val car = carOpt.get()

        car.lastLive = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))
        carRepo.save(car)

        logger.info("Livez: heartbeat signal received from $carId")
    }
}