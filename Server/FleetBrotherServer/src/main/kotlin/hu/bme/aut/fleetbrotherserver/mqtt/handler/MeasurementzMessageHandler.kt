package hu.bme.aut.fleetbrotherserver.mqtt.handler

import com.fasterxml.jackson.databind.ObjectMapper
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.*
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHandler
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

open class MeasurementzMessageHandler(private val measurementRepo: MeasurementRepository, private val carRepo: CarRepository) : MessageHandler {
    private val logger = LoggerFactory.getLogger(MeasurementzMessageHandler::class.java)!!

    @Transactional
    override fun handleMessage(message: Message<*>) {
        val messageRoot = ObjectMapper().readTree(message.payload.toString())

        val carId = messageRoot["id"].asInt()
        val carOpt = carRepo.findById(carId)

        if (carOpt.isEmpty) {
            return
        }
        val car = carOpt.get()

        val schemaId = messageRoot["schema"].asInt()
        val schemaCar = car.schemaCars.find {
            it.schema.id == schemaId
        } ?: return


        val data = messageRoot["data"].toPrettyString().trimIndent()

        val measurement = Measurement(-1, Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), data, schemaCar.schema, car)
        measurementRepo.save(measurement)
        logger.info("Measurementz: new data received from $carId")
    }

}