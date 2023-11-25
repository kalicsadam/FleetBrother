package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import kotlinx.serialization.json.*
import kotlinx.serialization.json.Json.Default.encodeToJsonElement
import org.eclipse.paho.client.mqttv3.*

import java.util.*
import kotlin.random.Random

class Client(
    var name: String,
    var serverURI: String
) {
    var id: String = UUID.randomUUID().toString()

    //var obu: OBU = OBU(serverURI,id)
    //var obuCallback: OBUCallback = OBUCallback()
    var mqttClient: MqttClient = MqttClient(serverURI, id)

    //var mqttCallback: MqttCallback = OBUCallback(this@Client)
    private var livezPeriod = 10L
    private var paramPeriod = 30L

    var tempUnit = TempUnit.Celsius
    var speedUnit = SpeedUnit.kph
    var distanceUnit = DistanceUnit.km
    var pressureUnit = PressureUnit.bar

    //var doors_locked by mutableStateOf(false)
    //var immobilized by mutableStateOf(false)
    //var latestAlert by mutableStateOf(OperatorAlert(AlertPriority.NONE, ""))
    //var latestAction by mutableStateOf(OperatorAction(Action.NONE))
    var engineStatus by mutableStateOf(engineStatus(EngineState.off, 0.0, tempUnit))
    var currentSpeed by mutableStateOf(currentSpeed(Random.nextInt(from = 20, until = 90), speedUnit))
    var currentlocation by mutableStateOf(location(latitude = Random.nextDouble(from = 45.7419, until = 48.5851), longitude = Random.nextDouble(from = 16.1136, until = 22.8968)))
    var temperature by mutableStateOf(temperature(Random.nextDouble(10.0,30.0), Random.nextDouble(10.0,40.0), tempUnit))
    var fuel by mutableStateOf(fuelLevel(Random.nextInt(80, 100)))
    var odometer by mutableStateOf(odometer(Random.nextDouble(0.0, 100000.0), distanceUnit, 0))
    var batteryStatus by mutableStateOf(batteryStatus(Random.nextInt(80, 100)))
    var tirePressure by mutableStateOf(tirePressure(pressureUnit, 0.0, 0.0, 0.0, 0.0))
    var oil by mutableStateOf(oil(FluidLevel.Refill_needed))
    var windshieldCleaner by mutableStateOf(windshieldCleaner(FluidLevel.Refill_needed))


    init {
        val mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isCleanSession = true
        mqttConnectOptions.connectionTimeout = 10

        //GlobalScope.launch {
        //withContext(Dispatchers.IO) {
        mqttClient.connect(mqttConnectOptions)
        //mqttClient.setCallback(mqttCallback)
        mqttClient.subscribe("livez")
        mqttClient.subscribe("measurementz")
        //mqttClient.subscribe("configs")
        //}
        //}
        startHeartbeat()
        startParameters()
    }

    private fun startParameters() {
        GlobalScope.launch {
            while (true) {
                delay(paramPeriod * 1000)
                sendParameters()
            }
        }
    }

    private fun sendParameters() {
        if (mqttClient.isConnected) {
            generateRandomParams()
            var msg = MqttMessage()
            msg.qos = 0
            msg.isRetained = true

            val engine_ = Json.encodeToJsonElement(engineStatus) as JsonObject
            val engine = buildJsonObject {
                put("schema", JsonPrimitive("engine_status"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    engine_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = engine.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val speed_= Json.encodeToJsonElement(currentSpeed) as JsonObject
            val speed = buildJsonObject {
                put("schema", JsonPrimitive("current_speed"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    speed_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = speed.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val location_ = Json.encodeToJsonElement(currentlocation) as JsonObject
            val location = buildJsonObject {
                put("schema", JsonPrimitive("location"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    location_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = location.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val temperature_ = Json.encodeToJsonElement(temperature) as JsonObject
            val temperature = buildJsonObject {
                put("schema", JsonPrimitive("temperature"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    temperature_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = temperature.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val fuel_ = Json.encodeToJsonElement(fuel) as JsonObject
            val fuel = buildJsonObject {
                put("schema", JsonPrimitive("fuel_level"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    fuel_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = fuel.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val odometer_ = Json.encodeToJsonElement(odometer) as JsonObject
            val odometer = buildJsonObject {
                put("schema", JsonPrimitive("odometer"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    odometer_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = odometer.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val battery_ = Json.encodeToJsonElement(batteryStatus) as JsonObject
            val battery = buildJsonObject {
                put("schema", JsonPrimitive("battery_status"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    battery_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = battery.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val tire_ = Json.encodeToJsonElement(tirePressure) as JsonObject
            val tire = buildJsonObject {
                put("schema", JsonPrimitive("tire_pressure"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    tire_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = tire.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val oil_ = Json.encodeToJsonElement(oil) as JsonObject
            val oil = buildJsonObject {
                put("schema", JsonPrimitive("oil"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    oil_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = oil.toString().toByteArray()
            mqttClient.publish("measurementz", msg)

            val windshield_ = Json.encodeToJsonElement(windshieldCleaner) as JsonObject
            val windshield = buildJsonObject {
                put("schema", JsonPrimitive("windshield_cleaner"))
                put("id", JsonPrimitive(id))
                putJsonObject("data"){
                    windshield_.forEach { (key, value) ->
                        put(key, value)
                    }
                }
            }
            msg.payload = windshield.toString().toByteArray()
            mqttClient.publish("measurementz", msg)
        }
    }

    private fun generateRandomParams(){
        engineStatus = engineStatus(
            EngineState.values().random(),
            Random.nextDouble(from = 90.0, until = 105.0),
            TempUnit.Celsius
        )
        currentSpeed = currentSpeed(Random.nextInt(from = currentSpeed.value - 10, until = currentSpeed.value + 10), speedUnit)
        currentlocation = location(
            latitude = Random.nextDouble(maxOf(45.7419, currentlocation.latitude - 0.5), minOf(48.5851,currentlocation.latitude + 0.5)),
            longitude = Random.nextDouble(maxOf(16.1136, currentlocation.longitude - 0.5), minOf(22.8968,currentlocation.longitude + 0.5))
        )
        temperature = temperature(Random.nextDouble(maxOf(10.0, temperature.internal - 0.5), minOf(30.0,temperature.internal + 0.5)), Random.nextDouble(maxOf(10.0, temperature.external - 0.5), minOf(40.0,temperature.external + 0.5)), tempUnit)
        fuel = fuelLevel(
            percentage =
            if (Random.nextInt(0, 100) % 16 == 0) {
                fuel.percentage - 1
            } else if (fuel.percentage < Random.nextInt(5, 20)) {
                Random.nextInt(60, 100)
            } else fuel.percentage
        )
        odometer = odometer(
            value =
            if (Random.nextInt(0, 100) % 3 == 0) {
                odometer.value + 1
            } else odometer.value,
            distanceUnit,
            rollbacks =
            if (odometer.value > Random.nextDouble(100000.0, 300000.0)) odometer.rollbacks + 1
            else odometer.rollbacks
        )
        batteryStatus = batteryStatus(
            percentage =
            if (Random.nextInt(0, 100) % 10 == 0) batteryStatus.percentage - 1
            else batteryStatus.percentage
        )
        tirePressure = tirePressure(
            pressureUnit,
            Random.nextDouble(1.8, 2.4),
            Random.nextDouble(1.8, 2.4),
            Random.nextDouble(1.8, 2.4),
            Random.nextDouble(1.8, 2.4)
        )
        oil = oil(
            level =
            if (Random.nextInt(0, 100) % 10 == 0) FluidLevel.Refill_needed
            else FluidLevel.Sufficient
        )
        windshieldCleaner = windshieldCleaner(
            level =
            if (Random.nextInt(0, 100) % 10 == 0) FluidLevel.Refill_needed
            else FluidLevel.Sufficient
        )
    }

    private fun startHeartbeat() {
        GlobalScope.launch {
            while (true) {
                delay(livezPeriod * 1000)
                sendHeartbeat()
            }
        }
    }

    private fun sendHeartbeat() {
        if (mqttClient.isConnected) {
            var livezjson = buildJsonObject {
                put("schema", JsonPrimitive("livez"))
                put("id", JsonPrimitive(id))
            }

            var msg = MqttMessage()
            msg.payload = livezjson.toString().toByteArray()
            msg.qos = 0
            msg.isRetained = true
            mqttClient.publish("livez", msg)
        }
    }

    fun mqttDisconnect() {
        mqttClient.unsubscribe("#")
        mqttClient.disconnect()
    }


}