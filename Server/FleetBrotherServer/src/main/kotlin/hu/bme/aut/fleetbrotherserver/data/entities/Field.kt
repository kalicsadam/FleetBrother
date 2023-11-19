package hu.bme.aut.fleetbrotherserver.data.entities

data class Field(
    var id: Int,
    var key: String,
    var type: Type,
    var isList: Boolean,
)
