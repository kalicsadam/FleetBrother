package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Schema
import hu.bme.aut.fleetbrotherserver.data.entities.SchemaCar
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository

@NoRepositoryBean
interface CarRepository : JpaRepository<Car, Int> {
    fun addSchema(carId: Int, schemaId: Int)
    fun addCar(car: Car,schema: Schema)
    fun removeCar(carId: Int, schemaId: Int)
    fun removeCar(car: Car,schema: Schema)
}

@Repository
class CarRepositoryImpl(
    private val entityManager: EntityManager
) : CarRepository, SimpleJpaRepository<Car, Int>(Car::class.java, entityManager) {

    @Transactional
    override fun addSchema(carId: Int, schemaId: Int) {
        val car = entityManager.find(Car::class.java, carId)
        val schema = entityManager.find(Schema::class.java, schemaId)
        addCar(car, schema)
    }

    @Transactional
    override fun addCar(car: Car, schema: Schema) {
        val schemaCar = SchemaCar(-1, schema, car)
        TODO("See if it actually works")
    }

    @Transactional
    override fun removeCar(carId: Int, schemaId: Int) {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun removeCar(car: Car, schema: Schema) {
        TODO("Not yet implemented")
    }
}