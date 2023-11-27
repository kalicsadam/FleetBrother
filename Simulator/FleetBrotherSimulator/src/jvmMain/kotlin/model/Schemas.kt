package model

import jdk.jfr.Percentage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObuConfig(
    var uuid: String,
    var id: String
)
/*@Serializable
data class IntermediateSchema(
    var schema: String,
    var id: String,
    var data: OperatorAlert,
)*/

/*@Serializable
data class OperatorAlert(
    var alertPriority: AlertPriority,
    var description: String
)

enum class AlertPriority{
    @SerialName("none")
    NONE,
    @SerialName("info")
    INFO,
    @SerialName("warning")
    WARNING,
    @SerialName("immediate_action")
    IMMEDIATE_ACTION
}

data class OperatorAction(
    var action: Action
)

enum class Action{NONE,IMMOBILIZE,MOBILIZE,LOCK_DOOR,UNLOCK_DOOR}
*/
/*
@Serializable
data class temperature(
    var engine: Double,
    var internal: Double,
    var external: Double,
    var unit: TempUnit
)
@Serializable
data class location(
    var longitude: Double,
    var latitude: Double,
)
@Serializable
data class currentSpeed(
    var value: Int,
    var unit: SpeedUnit
)
enum class SpeedUnit
{
    @SerialName("kph")
    kph,
    @SerialName("mph")
    mph
}
@Serializable
data class fuelLevel(
    var percentage: Int
)
@Serializable
data class engineStatus(
    var state: EngineState
)
enum class EngineState{
    @SerialName("running")
    running,
    @SerialName("off")
    off
}
enum class TempUnit
{
    @SerialName("c")
    Celsius,
    @SerialName("f")
    Fahrenheit
}
@Serializable
data class odometer(
    var value: Double,
    var unit: DistanceUnit,
    var rollbacks: Int
)
enum class DistanceUnit{
    @SerialName("km")
    km,
    @SerialName("mi")
    mi
}
@Serializable
data class batteryStatus(
    var percentage: Int,
)

@Serializable
data class tirePressure(
    var unit: PressureUnit,
    var front_left: Double,
    var front_right: Double,
    var back_left: Double,
    var back_right: Double
)
enum class PressureUnit
{
    @SerialName("bar")
    bar,
    @SerialName("psi")
    psi,
    @SerialName("kpa")
    KPa
}
enum class FluidLevel
{
    @SerialName("sufficient")
    Sufficient,
    @SerialName("needs_refill")
    Refill_needed
}
@Serializable
data class oil(
    var level: FluidLevel
)
@Serializable
data class windshieldCleaner(
    var level: FluidLevel
)*/