package hu.bme.aut.fleetbrotherserver.data.repositories

import hu.bme.aut.fleetbrotherserver.data.entities.Car
import hu.bme.aut.fleetbrotherserver.data.entities.Fleet
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface FleetRepository : JpaRepository<Fleet, Int>, CustomFleetRepository

interface CustomFleetRepository {
    fun addCar(fleetId: Int, carId: Int)
    fun addCar(fleet: Fleet, car: Car)
    fun removeCar(fleetId: Int, carId: Int)
    fun removeCar(fleet: Fleet, car: Car)

    fun deleteFleetSafely(fleetId: Int)
}

open class CustomFleetRepositoryImpl(
    private val entityManager: EntityManager
) : CustomFleetRepository {

    @Transactional
    override fun addCar(fleetId: Int, carId: Int) {
        val fleet = entityManager.find(Fleet::class.java, fleetId)
        val car = entityManager.find(Car::class.java, carId)
        addCar(fleet, car)
    }

    @Transactional
    override fun addCar(fleet: Fleet, car: Car) {
        fleet.cars.add(car)
        car.fleet = fleet
    }

    @Transactional
    override fun removeCar(fleetId: Int, carId: Int) {
        val fleet = entityManager.find(Fleet::class.java, fleetId)
        val car = entityManager.find(Car::class.java, carId)
        removeCar(fleet, car)
    }

    @Transactional
    override fun removeCar(fleet: Fleet, car: Car) {
        car.fleet = null
        fleet.cars.remove(car)
    }

    @Transactional
    override fun deleteFleetSafely(fleetId: Int) {
        val fleet = entityManager.find(Fleet::class.java, fleetId)
        val list = fleet.cars.toList()
        list.forEach{removeCar(fleet, it)}
        entityManager.remove(fleet)
    }
}