package hu.bme.aut.fleetbrotherserver.service

import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.FieldRepository
import hu.bme.aut.fleetbrotherserver.data.repositories.interfaces.SchemaRepository
import hu.bme.aut.fleetbrotherserver.dtos.FieldDto
import hu.bme.aut.fleetbrotherserver.dtos.SchemaCarAssignDto
import hu.bme.aut.fleetbrotherserver.dtos.SchemaDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertBackFromDto
import hu.bme.aut.fleetbrotherserver.dtos.mapper.convertToDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.SchemaService
import org.springframework.stereotype.Component

@Component
class SchemaServiceImpl(
    private val schemaRepository: SchemaRepository,
    private val fieldRepository: FieldRepository
) : SchemaService {
    override fun getAllSchemas(): List<SchemaDto> {
        val schemas = schemaRepository.findAll()
        val schemaDtos = schemas.map { it.convertToDto() }
        return schemaDtos
    }

    override fun getSchemasForCar(carId: Int): List<SchemaDto> {
        var schemas = schemaRepository.getSchemaByCar(carId)
        val schemaDtos = schemas.map { it.convertToDto() }
        return schemaDtos
    }

    override fun createSchema(schemaDto: SchemaDto) {
        val schema = schemaDto.convertBackFromDto()
        schemaRepository.save(schema)
    }

    override fun addFiledToSchema(schemaId: Int, filedDto: FieldDto) {
        var field = filedDto.convertBackFromDto()
        field = fieldRepository.save(field)
        schemaRepository.addField(schemaId, field.id)
    }

    override fun removeFieldFromSchema(schemaId: Int, fieldId: Int) {
        schemaRepository.removeField(schemaId, fieldId)
    }

    override fun deleteSchema(schemaId: Int) {
        schemaRepository.deleteById(schemaId)
    }

    override fun assignSchemaToCar(schemaId: Int, schemaCarAssignDto: SchemaCarAssignDto) {
        schemaCarAssignDto.carIdsToAdd.forEach { carId ->
            schemaRepository.addCar(schemaId, carId)
        }

        schemaCarAssignDto.carIdsToRemove.forEach { carId ->
            schemaRepository.removeCar(schemaId, carId)
        }
    }
}