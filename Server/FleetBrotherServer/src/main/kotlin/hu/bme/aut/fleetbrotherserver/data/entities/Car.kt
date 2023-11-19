package hu.bme.aut.fleetbrotherserver.data.entities

data class Car(
    var id: Int,
    var name: String,
    var licensePlate: String,
    var vin: String,

    var schema: MutableList<Schema>,
    var fleet: Fleet?,
    var alerts: MutableList<Alert>,
)
