package hu.bme.aut.fleetbrotherserver.controller

import hu.bme.aut.fleetbrotherserver.dtos.FieldDto
import hu.bme.aut.fleetbrotherserver.dtos.SchemaCarAssignDto
import hu.bme.aut.fleetbrotherserver.dtos.SchemaDto
import hu.bme.aut.fleetbrotherserver.service.interfaces.SchemaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/schema")
class SchemaController(
    private val schemaService: SchemaService
) {
    @GetMapping
    fun getAllSchemas(): List<SchemaDto> {
        return schemaService.getAllSchemas()
    }

    @GetMapping("/getSchemasForCar")
    fun getSchemasForCar(@RequestParam carId: Int) : List<SchemaDto> {
        return schemaService.getSchemasForCar(carId)
    }

    @PutMapping("/create")
    fun createSchema(@RequestBody schemaDto: SchemaDto) {
        schemaService.createSchema(schemaDto)
    }

    @PutMapping("/{schemaId}/addField")
    fun addFieldToSchema(@PathVariable schemaId: Int, @RequestBody fieldDto: FieldDto) {
        schemaService.addFiledToSchema(schemaId, fieldDto)
    }

    @PutMapping("/{schemaId}/assignSchemaToCar")
    fun assignSchemaToCar(@PathVariable schemaId: Int, @RequestBody schemaCarAssignDto: SchemaCarAssignDto) {
        schemaService.assignSchemaToCar(schemaId, schemaCarAssignDto)
    }

    @DeleteMapping("/{schemaId}/removeField")
    fun removeFieldFromSchema(@PathVariable schemaId: Int, @RequestParam fieldId: Int) {
        schemaService.removeFieldFromSchema(schemaId, fieldId)
    }

    @DeleteMapping("/{schemaId}")
    fun deleteSchema(@PathVariable schemaId: Int) {
        schemaService.deleteSchema(schemaId)
    }
}