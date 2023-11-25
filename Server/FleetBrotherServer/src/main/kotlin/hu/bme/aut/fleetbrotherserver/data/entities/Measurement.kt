package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
data class Measurement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var timestamp: Timestamp,
    var data: String,

    @ManyToOne
    @JoinColumn(name = "schema_id")
    var schema: Schema?,

    @ManyToOne
    @JoinColumn(name = "car_id")
     var car: Car?,

    @OneToMany(mappedBy = "measurement")
    var alertHistories: MutableList<AlertHistory> = mutableListOf(),
)
