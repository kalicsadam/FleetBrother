package hu.bme.aut.fleetbrotherserver.data.entities

data class Alert(
    var id: Int,
    var name: String,
    var keyName: String,
    var minValue: Double?,
    var maxValue: Double?,
    var exists: Boolean?,
    var forbiddenValue: String?,
    var car: Car,
)
