package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*

@Entity
data class Schema(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    var name: String,

    @OneToMany(mappedBy = "schema", orphanRemoval = true)
    var fields: MutableList<Field> = mutableListOf(),

    @OneToMany(mappedBy = "schema", orphanRemoval = true)
    var measurements: MutableList<Measurement> = mutableListOf(),

    @OneToMany(mappedBy = "schema")
    var schemaCars: MutableList<SchemaCar> = mutableListOf(),
)
