package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.MeasurementRepository
import hu.bme.aut.fleetbrotherserver.dtos.MeasurementDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.MeasurementService
import org.springframework.stereotype.Component

@Component
class MeasurementServiceImpl(
    private val measurementRepository: MeasurementRepository
) : MeasurementService {
    override fun getMeasurements(carId: Int, schemaId: Int): List<MeasurementDto> {
        val measurements = measurementRepository.getMeasurementsByCarAndSchema(carId, schemaId)
        val measurementDtos = measurements.map { it.convertToDto() }
        return measurementDtos
    }
}