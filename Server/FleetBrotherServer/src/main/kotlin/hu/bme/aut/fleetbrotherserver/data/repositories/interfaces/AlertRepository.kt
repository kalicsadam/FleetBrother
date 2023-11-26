package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Alert
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AlertRepository : JpaRepository<Alert, Int>, CustomAlertRepository {

    @Query("SELECT a FROM Alert a WHERE a.car.id = :carId")
    fun getAlertByCar(carId: Int) : List<Alert>
}

interface CustomAlertRepository {
    fun addMeasurement(alertId: Int, measurementId: Int)
    fun addMeasurement(alert: Alert, measurement: Measurement)
    fun removeMeasurement(alertId: Int, measurementId: Int)
    fun removeMeasurement(alert: Alert, measurement: Measurement)
}

open class CustomAlertRepositoryImpl(
    private val entityManager: EntityManager,
    private val alertHistoryRepository: CustomAlertHistoryRepository
) : CustomAlertRepository {
    @Transactional
    override fun addMeasurement(alertId: Int, measurementId: Int) {
        val alert = entityManager.find(Alert::class.java, alertId)
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        addMeasurement(alert, measurement)
    }

    @Transactional
    override fun addMeasurement(alert: Alert, measurement: Measurement) {
        alertHistoryRepository.addAlertHistory(alert, measurement)
    }

    @Transactional
    override fun removeMeasurement(alertId: Int, measurementId: Int) {
        val alert = entityManager.find(Alert::class.java, alertId)
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        removeMeasurement(alert, measurement)
    }

    @Transactional
    override fun removeMeasurement(alert: Alert, measurement: Measurement) {
        alertHistoryRepository.removeAlertHistory(alert, measurement)
    }

}