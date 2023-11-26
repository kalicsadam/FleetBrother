package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.AlertRepository
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.CarRepository
import hu.bme.aut.fleetbrotherserver.dtos.AlertDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertBackFromDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.AlertService
import org.springframework.stereotype.Component

@Component
class AlertServiceImpl(
    private val alertRepository: AlertRepository,
    private val carRepository: CarRepository
) : AlertService {
    override fun getAlerts(carId: Int): List<AlertDto> {
        val alserts = alertRepository.getAlertByCar(carId)
        val alertDtos = alserts.map { it.convertToDto() }
        return alertDtos
    }

    override fun createAlert(carId: Int, alertDto: AlertDto) {
        var alert = alertDto.convertBackFromDto()
        alert = alertRepository.save(alert)
        carRepository.addAlert(carId, alert.id)
    }

    override fun deleteAlert(alertId: Int) {
        alertRepository.deleteById(alertId)
    }
}