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
    var licensePlate: String,
    var vin: String,
    var serverURI: String
) {
    var uuid: String = UUID.randomUUID().toString()
    var carId: String? = null

    var mqttClient: MqttClient = MqttClient(serverURI, uuid)

    var livezPeriod by mutableStateOf(10L)

    init {
        val mqttConnectOptions = MqttConnectOptions()

        mqttConnectOptions.isCleanSession = true
        mqttConnectOptions.connectionTimeout = 10

        mqttClient.connect(mqttConnectOptions)
        mqttClient.subscribe("livez")
        mqttClient.subscribe("obu-config")
        mqttClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {}

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                when(topic){
                    "obu-config" -> {
                        var msg = Json.decodeFromString<ObuConfig>(message?.payload?.decodeToString()!!)
                        if(uuid==msg.uuid){
                            carId = msg.id
                            startHeartbeat()
                        }
                    }
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {}
        })

        acquireCarId()
    }

    fun acquireCarId() {
        GlobalScope.launch {
            while (carId.isNullOrEmpty()) {
                val msg = MqttMessage()
                msg.qos = 0
                msg.isRetained = true
                val connReq = buildJsonObject {
                    put("method", "register")
                    put("uuid", uuid)
                    put("name", name)
                    put("lincense_plate", licensePlate)
                    put("vin", vin)
                }
                msg.payload = connReq.toString().toByteArray()
                mqttClient.publish("livez", msg)
                delay(10000L)
            }
        }
    }

    fun startHeartbeat() {
        GlobalScope.launch {
            while (true) {
                delay(livezPeriod * 1000)
                sendHeartbeat()
            }
        }
    }

    private fun sendHeartbeat() {
        if (mqttClient.isConnected) {
            val livezjson = buildJsonObject {
                put("method", "heartbeat")
                put("id", carId)
            }

            val msg = MqttMessage()
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