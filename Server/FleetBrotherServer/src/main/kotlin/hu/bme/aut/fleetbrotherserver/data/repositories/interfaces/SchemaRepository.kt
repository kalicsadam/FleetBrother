package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Field
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement
import hu.bme.aut.fleetbrotherserver.data.entities.Schema
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface SchemaRepository : JpaRepository<Schema, Int>, CustomSchemaRepository

interface CustomSchemaRepository {
    fun addCar(schemaId: Int, carId: Int)
    fun addCar(schema: Schema, car: Car)
    fun removeCar(schemaId: Int, carId: Int)
    fun removeCar(schema: Schema, car: Car)

    fun addField(schemaId: Int, fieldId: Int)
    fun addField(schema: Schema, field: Field)
    fun removeField(schemaId: Int, fieldId: Int)
    fun removeField(schema: Schema, field: Field)

    fun addMeasurement(schemaId: Int, measurementId: Int)
    fun addMeasurement(schema: Schema, measurement: Measurement)
    fun removeMeasurement(schemaId: Int, measurementId: Int)
    fun removeMeasurement(schema: Schema, measurement: Measurement)
}

open class CustomSchemaRepositoryImpl(
    private val entityManager: EntityManager,
    private val schemaCarRepository: SchemaCarRepository
) : CustomSchemaRepository {

    @Transactional
    override fun addCar(schemaId: Int, carId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        addCar(schema, car)
    }

    @Transactional
    override fun addCar(schema: Schema, car: Car) {
        schemaCarRepository.addSchemaCar(car, schema)
    }

    @Transactional
    override fun removeCar(schemaId: Int, carId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        removeCar(schema, car)
    }

    @Transactional
    override fun removeCar(schema: Schema, car: Car) {
        schemaCarRepository.removeSchemaCar(car, schema)
    }

    @Transactional
    override fun addField(schemaId: Int, fieldId: Int) {
        val field = entityManager.find(Field::class.java, fieldId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        addField(schema, field)
    }

    @Transactional
    override fun addField(schema: Schema, field: Field) {
        field.schema = schema
        schema.fields.add(field)
    }

    @Transactional
    override fun removeField(schemaId: Int, fieldId: Int) {
        val field = entityManager.find(Field::class.java, fieldId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        removeField(schema, field)
    }

    @Transactional
    override fun removeField(schema: Schema, field: Field) {
        field.schema = null
        schema.fields.remove(field)
    }

    @Transactional
    override fun addMeasurement(schemaId: Int, measurementId: Int) {
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        addMeasurement(schema, measurement)
    }

    @Transactional
    override fun addMeasurement(schema: Schema, measurement: Measurement) {
        measurement.schema = schema
        schema.measurements.add(measurement)
    }

    @Transactional
    override fun removeMeasurement(schemaId: Int, measurementId: Int) {
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        removeMeasurement(schema, measurement)
    }

    @Transactional
    override fun removeMeasurement(schema: Schema, measurement: Measurement) {
        measurement.schema = null
        schema.measurements.remove(measurement)
    }
}