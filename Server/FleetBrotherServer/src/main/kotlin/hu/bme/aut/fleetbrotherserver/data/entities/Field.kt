package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*

@Entity
data class Field(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var isList: Boolean,

    @Column(name = "filedKey")
    var key: String,

    @Enumerated(EnumType.STRING)
    var type: Type,

    @ManyToOne
    @JoinColumn(name = "schema_id")
    var schema: Schema? = null,
)
