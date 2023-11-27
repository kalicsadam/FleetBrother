package hu.bme.aut.fleetbrotherserver.data.entities

import jakarta.persistence.*

@Entity
@Table(name = "user", schema = "public")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator",  sequenceName = "user_id_seq")
    var id: Int,
    var email: String,
    var passwordHash: String,

    var role: String
)