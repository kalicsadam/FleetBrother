package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.AlertDto
import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement

interface AlertService {
    fun getAlerts(carId: Int) : List<AlertDto>
    fun createAlert(carId: Int, alertDto: AlertDto)
    fun deleteAlert(alertId: Int)
    fun checkAlerts(car: Car, measurement: Measurement)
}