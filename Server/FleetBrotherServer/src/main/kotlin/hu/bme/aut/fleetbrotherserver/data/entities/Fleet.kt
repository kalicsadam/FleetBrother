package hu.bme.aut.fleetbrotherserver.data.entities

data class Fleet(
    var id: Int,
    var name: String,
    var description: String,

    var cars: MutableList<Car>,
)
