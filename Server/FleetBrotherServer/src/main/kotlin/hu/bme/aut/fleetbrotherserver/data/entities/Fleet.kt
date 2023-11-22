package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Fleet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var description: String,

    @OneToMany(mappedBy = "fleet", orphanRemoval = true)
    var cars: MutableList<Car> = mutableListOf(),
)
