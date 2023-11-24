package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*

@Entity
data class Fleet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var description: String,


    @OneToMany(mappedBy = "fleet")
    var cars: MutableList<Car> = mutableListOf(),
)
