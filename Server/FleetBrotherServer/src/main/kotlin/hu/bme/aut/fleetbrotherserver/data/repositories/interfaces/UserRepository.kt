package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Int> {
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.email = :email")
    fun findUserByEmail(email: String) : User
}