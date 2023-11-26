package hu.bme.aut.fleetbrotherserver.service

import com.fasterxml.jackson.databind.ObjectMapper
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.CarRepository
import hu.bme.aut.fleetbrotherserver.service.interfaces.LivezHandlerService
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class LivezHandlerServiceImpl(private val carRepo: CarRepository) : LivezHandlerService {
    private val logger = LoggerFactory.getLogger(LivezHandlerServiceImpl::class.java)!!

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