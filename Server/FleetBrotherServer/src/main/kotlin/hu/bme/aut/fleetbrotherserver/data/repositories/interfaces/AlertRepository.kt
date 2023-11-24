package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.Alert
import org.springframework.data.jpa.repository.JpaRepository

interface AlertRepository : JpaRepository<Alert, Int>