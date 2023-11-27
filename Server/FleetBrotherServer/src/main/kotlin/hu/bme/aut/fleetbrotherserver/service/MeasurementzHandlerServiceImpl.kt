package hu.bme.aut.fleetbrotherserver.service

import com.fasterxml.jackson.databind.ObjectMapper
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.*
import hu.bme.aut.fleetbrotherserver.service.interfaces.AlertService
import hu.bme.aut.fleetbrotherserver.service.interfaces.MeasurementzHandlerService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class MeasurementzHandlerServiceImpl(
        private val measurementRepo: MeasurementRepository,
        private val carRepo: CarRepository,
        private val alertService: AlertService,
) : MeasurementzHandlerService {
    private val logger = LoggerFactory.getLogger(MeasurementzHandlerServiceImpl::class.java)!!

    @Transactional
    override fun handleMessage(message: Message<*>) {
        val messageRoot = ObjectMapper().readTree(message.payload.toString())

        val carId = messageRoot["id"].asInt()
        val carOpt = carRepo.findById(carId)

        if (carOpt.isEmpty) {
            logger.error("Invalid car identifier received: $carId")
            return
        }
        val car = carOpt.get()

        val schemaName = messageRoot["schema"].asText()
        val schemaCar = car.schemaCars.find {
            it.schema.name == schemaName
        }
        if(schemaCar == null) {
            logger.error("Invalid schema identifier received: $schemaName")
            return
        }

        val data = messageRoot["data"].toPrettyString().trimIndent()

        val measurementSave = Measurement(-1, Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), data, schemaCar.schema, car)
        val measurement = measurementRepo.save(measurementSave)
        logger.info("Measurementz: new measurement received from: $carId")

        alertService.checkAlerts(car, measurement)
    }

}