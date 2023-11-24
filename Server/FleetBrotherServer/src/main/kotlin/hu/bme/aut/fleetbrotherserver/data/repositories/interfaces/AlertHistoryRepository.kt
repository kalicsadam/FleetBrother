package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Alert
import hu.bme.aut.fleetbrotherserver.data.entities.AlertHistory
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface AlertHistoryRepository : JpaRepository<AlertHistory, Int>, CustomAlertHistoryRepository

interface CustomAlertHistoryRepository {
    fun addAlertHistory(alert: Alert, measurement: Measurement) : AlertHistory
    fun removeAlertHistory(alert: Alert, measurement: Measurement)
}

open class CustomAlertHistoryRepositoryImpl(
    private val entityManager: EntityManager,
) : CustomAlertHistoryRepository {
    @Transactional
    override fun addAlertHistory(alert: Alert, measurement: Measurement) : AlertHistory {
        val alertHistory = AlertHistory(0, measurement, alert)
        alertHistory.alert = alert
        alert.alertHistories.add(alertHistory)
        alertHistory.measurement = measurement
        measurement.alertHistories.add(alertHistory)

        entityManager.persist(alertHistory)

        return alertHistory
    }

    override fun removeAlertHistory(alert: Alert, measurement: Measurement) {
        val alertHistory = entityManager.createQuery("SELECT ah FROM AlertHistory ah WHERE ah.alert.id = :alertId AND ah.measurement.id = :measurementId")
            .setParameter("alertId", alert.id)
            .setParameter("measurementId", measurement.id)
            .singleResult as AlertHistory

        alert.alertHistories.removeIf { it.id == alertHistory.id }
        measurement.alertHistories.removeIf { it.id == alertHistory.id }

        entityManager.remove(alertHistory)
    }
}