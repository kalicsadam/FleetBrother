package hu.bme.aut.fleetbrotherserver.data.entities

data class Schema(
    var id: Int,
    var fields: MutableList<Field>,


    var car: MutableList<Car>,
)
