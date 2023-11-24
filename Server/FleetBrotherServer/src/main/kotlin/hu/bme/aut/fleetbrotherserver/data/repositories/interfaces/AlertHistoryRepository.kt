package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.AlertHistory
import org.springframework.data.jpa.repository.JpaRepository

interface AlertHistoryRepository : JpaRepository<AlertHistory, Int>