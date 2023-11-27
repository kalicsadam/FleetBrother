package hu.bme.aut.fleetbrotherserver.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.CarRepository
import hu.bme.aut.fleetbrotherserver.mqtt.MqttGateway
import hu.bme.aut.fleetbrotherserver.service.interfaces.LivezHandlerService
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.stereotype.Service
import java.lang.Exception
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

data class CarRegistration(
    val method: String,
    val uuid: String,
    val name: String,
    val license_plate: String,
    val vin: String
)
data class HeartbeatSignal(
    val method: String,
    val id: Int
)

data class CarRegistrationAck(
    val uuid: String,
    val id: Int
)

@Service
class LivezHandlerServiceImpl(
    private val carRepo: CarRepository,
    private val mqttGateway: MqttGateway
) : LivezHandlerService {
    private val logger = LoggerFactory.getLogger(LivezHandlerServiceImpl::class.java)!!

    override fun handleMessage(message: Message<*>) {
        val objectMapper = jacksonObjectMapper()

        val messageRoot = objectMapper.readTree(message.payload.toString())
        val method = messageRoot["method"]
        if(method == null || method.isNull || !method.isTextual) {
            logger.error("Livez: Invalid message received.")
            return
        }

        when(method.asText()) {
            "register" -> {
                try {
                    val registration = objectMapper.readValue(message.payload.toString(), CarRegistration::class.java)
                    registerCar(registration)
                }
                catch(_: Exception) {
                    logger.error("Livez: Invalid registration message received.")
                }
            }
            "heartbeat" -> {
                try {
                    val heartbeatSignal = objectMapper.readValue(message.payload.toString(), HeartbeatSignal::class.java)
                    acknowledgeClientAlive(heartbeatSignal)
                }
                catch(_: Exception) {
                    logger.error("Livez: Invalid heartbeat message received.")
                }
            }
            else -> {
                logger.error("Livez: Invalid method received.")
            }
        }
    }

    private fun registerCar(carRegistration: CarRegistration) {
        val pendingCar = Car(
            -1,
            carRegistration.name,
            carRegistration.license_plate,
            carRegistration.vin,
            Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)),
            mutableListOf(),
            null,
            mutableListOf(),
            )

        val registeredCar = carRepo.save(pendingCar)

        val ack = CarRegistrationAck(
            carRegistration.uuid,
            registeredCar.id
        )

        val ackPayload = ObjectMapper().writeValueAsString(ack)
        mqttGateway.sendAcknowledgement(ackPayload)
        logger.info("Livez: Car registration message received from vehicle with uuid: ${carRegistration.uuid}")
    }

    private fun acknowledgeClientAlive(heartbeatSignal: HeartbeatSignal) {
        val carOpt = carRepo.findById(heartbeatSignal.id)

        if(carOpt.isEmpty) {
            logger.error("Livez: Invalid car ID received.")
            return
        }

        val car = carOpt.get()

        car.lastLive = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))
        carRepo.save(car)

        logger.info("Livez: heartbeat signal received from ${heartbeatSignal.id}")
    }
}