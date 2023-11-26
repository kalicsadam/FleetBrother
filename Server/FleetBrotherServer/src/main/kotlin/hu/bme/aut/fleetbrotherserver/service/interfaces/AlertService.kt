package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.AlertDto

interface AlertService {
    fun getAlerts(carId: Int) : List<AlertDto>
    fun createAlert(carId: Int, alertDto: AlertDto)
    fun deleteAlert(alertId: Int)
}