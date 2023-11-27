package hu.bme.aut.fleetbrotherserver.data.repositories.interfaces

import hu.bme.aut.fleetbrotherserver.data.entities.User
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.Transactional

interface UserRepository : JpaRepository<User, Int>, CustomUserRepository {
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.email = :email")
    fun findUserByEmail(email: String) : User
}

interface CustomUserRepository {
    fun saveUserWithHashing(user: User)
}

open class CustomUserRepositoryImpl(
    private val entityManager: EntityManager
) : CustomUserRepository {

    @Transactional
    override fun saveUserWithHashing(user: User) {
        user.passwordHash = BCryptPasswordEncoder().encode(user.passwordHash)
        entityManager.persist(user)
    }
}