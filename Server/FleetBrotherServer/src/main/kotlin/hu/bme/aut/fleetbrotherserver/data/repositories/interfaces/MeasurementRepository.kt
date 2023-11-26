package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Alert
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MeasurementRepository : JpaRepository<Measurement, Int>, CustomMeasurementRepository {
    @Query("SELECT m " +
            "FROM Measurement m " +
            "WHERE m.car.id = :carId AND m.schema.id = :schemaId")
    fun getMeasurementsByCarAndSchema(carId: Int, schemaId: Int) : List<Measurement>
}
interface CustomMeasurementRepository {
    fun addAlert(measurementId: Int, alertId: Int)
    fun addAlert(measurement: Measurement, alert: Alert)
    fun removeAlert(measurementId: Int, alertId: Int)
    fun removeAlert(measurement: Measurement, alert: Alert)
}
open class CustomMeasurementRepositoryImpl(
    private val entityManager: EntityManager,
    private val alertHistoryRepository: CustomAlertHistoryRepository
) : CustomMeasurementRepository {
    @Transactional
    override fun addAlert(measurementId: Int, alertId: Int) {
        val alert = entityManager.find(Alert::class.java, alertId)
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        addAlert(measurement, alert)
    }

    @Transactional
    override fun addAlert(measurement: Measurement, alert: Alert) {
        alertHistoryRepository.addAlertHistory(alert, measurement)
    }

    @Transactional
    override fun removeAlert(measurementId: Int, alertId: Int) {
        val alert = entityManager.find(Alert::class.java, alertId)
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        removeAlert(measurement, alert)
    }

    @Transactional
    override fun removeAlert(measurement: Measurement, alert: Alert) {
        alertHistoryRepository.removeAlertHistory(alert, measurement)
    }
}