package hu.bme.aut.fleetbrotherserver.data.entities

data class AlertHistory(
    var id: Int,
    var measurement: Measurement,
    var alert: Alert,
)
