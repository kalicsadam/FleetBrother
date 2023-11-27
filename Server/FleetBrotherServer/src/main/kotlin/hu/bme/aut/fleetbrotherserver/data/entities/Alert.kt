package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*

@Entity
data class Alert(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var keyName: String,
    var minValue: Double?,
    var maxValue: Double?,
    var forbiddenValue: String?,

    @Column(name = "existsBoolean")
    var exists: Boolean?,

    @ManyToOne
    @JoinColumn(name = "car_id")
    var car: Car? = null,

    @OneToMany(mappedBy = "alert")
    var alertHistories: MutableList<AlertHistory> = mutableListOf(),

    var isDeleted: Boolean = false
)
