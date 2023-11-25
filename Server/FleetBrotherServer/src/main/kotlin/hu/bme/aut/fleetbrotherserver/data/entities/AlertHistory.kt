package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*

@Entity
data class AlertHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    var measurement: Measurement,

    @ManyToOne
    @JoinColumn(name = "alert_id")
    var alert: Alert
)
