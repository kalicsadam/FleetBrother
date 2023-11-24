package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Alert
import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Measurement
import hu.bme.aut.fleetbrotherserver.data.entities.Schema
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, Int>, CustomCarRepository

interface CustomCarRepository {
    fun addSchema(carId: Int, schemaId: Int)
    fun addSchema(car: Car,schema: Schema)
    fun removeSchema(carId: Int, schemaId: Int)
    fun removeSchema(car: Car,schema: Schema)

    fun addAlert(carId: Int, alertId: Int)
    fun addAlert(car: Car, alert: Alert)
    fun removeAlert(carId: Int, alertId: Int)
    fun removeAlert(car: Car, alert: Alert)

    fun addMeasurement(carId: Int, measurementId: Int)
    fun addMeasurement(car: Car, measurement: Measurement)
    fun removeMeasurement(carId: Int, measurementId: Int)
    fun removeMeasurement(car: Car, measurement: Measurement)

}


open class CustomCarRepositoryImpl(
    private val entityManager: EntityManager,
    private val schemaCarRepository: SchemaCarRepository,
) : CustomCarRepository {

    @Transactional
    override fun addSchema(carId: Int, schemaId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        addSchema(car, schema)
    }

    @Transactional
    override fun addSchema(car: Car, schema: Schema) {
        schemaCarRepository.addSchemaCar(car, schema)
    }

    @Transactional
    override fun removeSchema(carId: Int, schemaId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        removeSchema(car, schema)
    }

    @Transactional
    override fun removeSchema(car: Car, schema: Schema) {
        schemaCarRepository.removeSchemaCar(car, schema)
    }

    override fun addAlert(carId: Int, alertId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val alert = entityManager.find(Alert::class.java, alertId)
        addAlert(car, alert)
    }

    override fun addAlert(car: Car, alert: Alert) {
        car.alerts.add(alert)
        alert.car = car
    }

    override fun removeAlert(carId: Int, alertId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val alert = entityManager.find(Alert::class.java, alertId)
        removeAlert(car, alert)
    }

    override fun removeAlert(car: Car, alert: Alert) {
        car.alerts.remove(alert)
        alert.car = null
    }

    override fun addMeasurement(carId: Int, measurementId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        addMeasurement(car, measurement)
    }

    override fun addMeasurement(car: Car, measurement: Measurement) {
        car.measurements.add(measurement)
        measurement.car = car
    }

    override fun removeMeasurement(carId: Int, measurementId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val measurement = entityManager.find(Measurement::class.java, measurementId)
        removeMeasurement(car, measurement)
    }

    override fun removeMeasurement(car: Car, measurement: Measurement) {
        car.measurements.remove(measurement)
        measurement.car = null
    }
}