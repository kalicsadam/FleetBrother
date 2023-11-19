package hu.bme.aut.fleetbrotherserver.data.entities

import java.sql.Timestamp

data class Measurement(
    var id: Int,
    var timestamp: Timestamp,
    var data: String,

    var schema: Schema,
    var car: Car
)
