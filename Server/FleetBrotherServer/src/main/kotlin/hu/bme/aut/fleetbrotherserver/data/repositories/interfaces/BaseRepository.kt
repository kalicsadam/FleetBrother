package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Car

interface BaseRepository<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
    fun create(entity: T)
    fun update(entity: T)
    fun delete(id: T)
}