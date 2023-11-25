package model

import kotlinx.coroutines.*
import kotlinx.coroutines.swing.Swing
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import org.eclipse.paho.client.mqttv3.*

import java.util.*
import java.util.concurrent.Executors

class Client(
    var name: String,
    var serverURI: String
) {
    var id: String = UUID.randomUUID().toString()
    var mqttclient: MqttClient = MqttClient(serverURI, id)
    private var livez_period = 60L

    init {
        val mqttConnectOptions = MqttConnectOptions()
        mqttConnectOptions.isCleanSession = true;
        mqttConnectOptions.connectionTimeout = 10;

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                mqttclient.connect(mqttConnectOptions)
            }
        }
        mqttclient.subscribe("livez")
        mqttclient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                when(topic){
                    "livez" -> livezconf(message)
                    "measurementz" -> {}
                    "configurations" -> configurations(message)
                    else -> return
                }
                //println("Received message from topic: $topic")
                //println("Message: ${String(message?.payload ?: ByteArray(0))}")

            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                TODO("Not yet implemented")
            }
        })

        //heartbeat goes in while loop
        heartbeat()

    }

    fun livezconf(msg: MqttMessage?){
        val livezReportingConf = Json.decodeFromString<LivezReportingConf>(msg?.payload.toString())
        if (livezReportingConf.id == id){
            livez_period = livezReportingConf.newPeriod.toLong()
        }
    }

    fun configurations(msg: MqttMessage?){
        
    }

    fun heartbeat() {
        GlobalScope.launch {
            while (true) {
                delay(livez_period * 1000)
                if (mqttclient.isConnected) {

                    var livezjson = buildJsonObject {
                        put("schema", JsonPrimitive("livez"))
                        put("id", JsonPrimitive(id))
                    }

                    var msg = MqttMessage()
                    msg.payload = livezjson.toString().toByteArray()
                    msg.qos = 0
                    msg.isRetained = true
                    mqttclient.publish("livez", msg)
                }
            }
        }

    }
}