package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.FieldDto
import hu.bme.aut.fleetbrotherserver.dtos.SchemaCarAssignDto
import hu.bme.aut.fleetbrotherserver.dtos.SchemaDto

interface SchemaService {
    fun getAllSchemas() : List<SchemaDto>
    fun getSchemasForCar(carId: Int) : List<SchemaDto>
    fun createSchema(schemaDto: SchemaDto)
    fun addFiledToSchema(schemaId: Int, filedDto: FieldDto)
    fun removeFieldFromSchema(schemaId: Int, fieldId: Int)
    fun deleteSchema(schemaId: Int)
    fun assignSchemaToCar(schemaId: Int, schemaCarAssignDto: SchemaCarAssignDto)
}