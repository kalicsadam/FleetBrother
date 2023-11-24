package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*

@Entity
data class SchemaCar(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @ManyToOne
    @JoinColumn(name = "schema_id")
    var schema: Schema,

    @ManyToOne
    @JoinColumn(name = "car_id")
    var car: Car,
)
