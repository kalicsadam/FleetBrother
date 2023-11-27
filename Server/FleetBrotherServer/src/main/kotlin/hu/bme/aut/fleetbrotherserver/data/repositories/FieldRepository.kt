package hu.bme.aut.fleetbrotherserver.data.repositories

import hu.bme.aut.fleetbrotherserver.data.entities.Field
import org.springframework.data.jpa.repository.JpaRepository

interface FieldRepository : JpaRepository<Field, Int>