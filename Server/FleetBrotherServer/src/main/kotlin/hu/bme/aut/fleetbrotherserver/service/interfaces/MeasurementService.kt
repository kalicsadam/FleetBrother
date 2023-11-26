package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.MeasurementDto

interface MeasurementService {
    fun getMeasurements(carId: Int, schemaId: Int) : List<MeasurementDto>
}