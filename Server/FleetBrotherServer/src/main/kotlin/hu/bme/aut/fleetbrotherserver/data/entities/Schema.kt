package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany

@Entity
data class Schema(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @OneToMany(mappedBy = "schema", orphanRemoval = true)
    var fields: MutableList<Field> = mutableListOf(),

    @OneToMany(mappedBy = "schema", orphanRemoval = true)
    var measurements: MutableList<Measurement> = mutableListOf(),


    @OneToMany(mappedBy = "schema", orphanRemoval = true)
    var schemaCars: MutableList<SchemaCar> = mutableListOf(),
)
