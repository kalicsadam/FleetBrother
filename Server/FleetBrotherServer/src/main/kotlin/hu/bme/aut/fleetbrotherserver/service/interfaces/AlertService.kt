package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement

interface AlertService {
    fun checkAlerts(car: Car, measurement: Measurement)
}