package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.AlertHistory

interface NotificationService {
    fun dispatchFirebaseAlert(alertHistory: AlertHistory)
    fun dispatchEmailAlert(alertHistory: AlertHistory)
}