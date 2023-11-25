package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
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
    var schema: Schema,

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id")
     var car: Car
)
