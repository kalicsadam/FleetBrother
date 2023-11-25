package hu.bme.aut.fleetbrotherserver.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import hu.bme.aut.fleetbrotherserver.data.entities.*
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.AlertHistoryRepository
import hu.bme.aut.fleetbrotherserver.mqtt.handler.MeasurementzMessageHandler
import hu.bme.aut.fleetbrotherserver.service.interfaces.AlertService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AlertServiceImpl(private val alertHistoryRepository: AlertHistoryRepository) : AlertService {
    private val logger = LoggerFactory.getLogger(AlertServiceImpl::class.java)!!

    override fun checkAlerts(car: Car, measurement: Measurement) {
        // If it gets to this point, we've already checked
        // whether the car has a schema. So this cast is safe.
        val schema = measurement.schema!!
        val dataRoot = ObjectMapper().readTree(measurement.data)

        for(alert in car.alerts) {
            val fieldMetadata = schema.fields.find {
                it.key == alert.keyName
            }
            if(fieldMetadata == null) {
                logger.error("An alert was created for a non existent field \"${alert.keyName}\" in schema \"${schema.name}\".")
                continue
            }

            val field = dataRoot[alert.keyName]
            if(field == null || field.isNull) continue

            if(shouldAlert(field, fieldMetadata, alert)) {
                alertHistoryRepository.addAlertHistory(alert, measurement)
                logger.info("An alert was dispatched for measurement with id: ${measurement.id}")
            }
        }
    }

    private fun shouldAlert(field: JsonNode, fieldMetadata: Field, alert: Alert): Boolean {
        return when {
            // Existence check was moved here, because it doesn't
            // need to be checked for every list value.
            // Regardless of the field being list-like, if there is such
            // a field with a non-null literal, we need to alert.
            // Additionally, checking whether it equals true was necessary
            // because exists can be null, and we only need to alert
            // if it's true, we ignore it otherwise.
            alert.exists == true -> true
            !fieldMetadata.isList -> isAlertConditionMet(field, fieldMetadata.type, alert)
            fieldMetadata.isList -> field.any { isAlertConditionMet(it, fieldMetadata.type, alert) }
            else -> false
        }
    }

    private fun isAlertConditionMet(field: JsonNode, type: Type, alert: Alert): Boolean {
        return when {
            alert.forbiddenValue != null && field.asText() == alert.forbiddenValue -> true
            // Force cast was necessary because the compiler insisted that
            // the values could've been changed after the null check, because they're mutable.
            type == Type.NUMBER && (
                    alert.minValue != null && field.asDouble() < alert.minValue!! ||
                    alert.maxValue != null && field.asDouble() > alert.maxValue!!) -> true
            else -> false
        }
    }
}