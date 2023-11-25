package hu.bme.aut.fleetbrotherserver.service.interfaces

import hu.bme.aut.fleetbrotherserver.dtos.FieldDto
import hu.bme.aut.fleetbrotherserver.dtos.SchemaDto

interface SchemaService {
    fun getAllSchemas() : List<SchemaDto>
    fun createSchema(schemaDto: SchemaDto)
    fun addFiledToSchema(schemaId: Int, filedDto: FieldDto)
    fun removeFieldFromSchema(schemaId: Int, fieldId: Int)
    fun deleteSchema(schemaId: Int)
}