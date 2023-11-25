package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Schema
import hu.bme.aut.fleetbrotherserver.data.entities.SchemaCar
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository


interface SchemaCarRepository : JpaRepository<SchemaCar, Int>, CustomSchemaCarRepository {
//    @Query("SELECT sc FROM SchemaCar sc WHERE sc.car.id = :carId AND sc.schema.id = :schemaId")
//    fun getByCarAndSchema(carId: Int, schemaId: Int) : SchemaCar
}

interface CustomSchemaCarRepository {
    fun addSchemaCar(car: Car, schema: Schema) : SchemaCar
    fun removeSchemaCar(car: Car, schema: Schema)
}

open class CustomSchemaCarRepositoryImpl(
    private val entityManager: EntityManager,
) : CustomSchemaCarRepository {

    @Transactional
    override fun addSchemaCar(car: Car, schema: Schema) : SchemaCar {
        val schemaCar = SchemaCar(0, schema, car)
        schemaCar.car = car
        car.schemaCars.add(schemaCar)
        schemaCar.schema = schema
        schema.schemaCars.add(schemaCar)

        entityManager.persist(schemaCar)

        return schemaCar
    }

    @Transactional
    override fun removeSchemaCar(car: Car, schema: Schema) {
        val schemaCar = entityManager.createQuery("SELECT sc FROM SchemaCar sc WHERE sc.car.id = :carId AND sc.schema.id = :schemaId")
            .setParameter("carId", car.id)
            .setParameter("schemaId", schema.id)
            .singleResult as SchemaCar


        car.schemaCars.removeIf { it.id == schemaCar.id }
        schema.schemaCars.removeIf { it.id == schemaCar.id }

        entityManager.remove(schemaCar)
    }
}