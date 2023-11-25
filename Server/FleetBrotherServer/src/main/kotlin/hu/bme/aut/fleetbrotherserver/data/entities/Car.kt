package hu.bme.aut.fleetbrotherserver.data.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var licensePlate: String,
    var vin: String,

    @OneToMany(mappedBy = "car", orphanRemoval = true)
    var schemaCars: MutableList<SchemaCar> = mutableListOf(),

    @ManyToOne(targetEntity = Fleet::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fleet_id")
    @JsonBackReference
    var fleet: Fleet? = null,


    @OneToMany(mappedBy = "car")
    var alerts: MutableList<Alert>,

    @OneToMany(mappedBy = "car", orphanRemoval = true)
    var measurements: MutableList<Measurement> = mutableListOf(),
)
