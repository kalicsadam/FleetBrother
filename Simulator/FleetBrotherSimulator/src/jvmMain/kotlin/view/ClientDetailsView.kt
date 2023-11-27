package view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.serialization.json.*
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.json.JSONObject
import viewmodel.ClientDetailsViewModel
import java.lang.Exception

@Composable
fun detailsView(clientDetailsViewModel: ClientDetailsViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 20.dp
    ) {
        var livez by remember { mutableStateOf(clientDetailsViewModel.client?.livezPeriod.toString()) }
        var schema by remember { mutableStateOf("") }
        var data by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp).verticalScroll(state = ScrollState(0))
        ) {

            IconButton(
                onClick = { clientDetailsViewModel.visible = !clientDetailsViewModel.visible }
            ) { Icon(imageVector = Icons.Default.Close, contentDescription = null) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1.0f)) {
                    Text(
                        modifier = Modifier.padding(start=20.dp, bottom = 20.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        text = clientDetailsViewModel.client!!.name
                    )
                    Text(
                        modifier = Modifier.padding(start=20.dp, bottom = 20.dp),
                        fontSize = 15.sp,
                        text = "License plate: ${clientDetailsViewModel.client!!.licensePlate}"
                    )
                    Text(
                        modifier = Modifier.padding(start=20.dp, bottom = 20.dp),
                        fontSize = 15.sp,
                        text = "VIN: ${clientDetailsViewModel.client!!.vin}"
                    )
                    Text(
                        modifier = Modifier.padding(start=20.dp, bottom = 20.dp),
                        fontSize = 15.sp,
                        text = "uuid: ${clientDetailsViewModel.client!!.uuid}"
                    )
                }
                Column {
                    Text("Livez period:")
                    Row {
                        TextField(
                            modifier = Modifier.width(100.dp),
                            value = livez,
                            onValueChange = { txt ->
                                livez = txt
                            }
                        )
                        Button(
                            modifier = Modifier.padding(start = 15.dp, end = 15.dp),
                            onClick = {
                                clientDetailsViewModel.client?.livezPeriod = livez.toLong()
                            },
                            enabled = ((livez.isNotEmpty() && livez.matches("^[0-9]*\$".toRegex())))
                        ) { Text("Set") }
                    }
                }

            }
            Divider()
            Text(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp, top = 20.dp), text = "Schema ID")
            TextField(
                modifier = Modifier.padding(start = 20.dp, bottom = 20.dp),
                value = schema,
                onValueChange = { txt ->
                    schema = txt
                }
            )
            Text(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp), text = "Data")
            TextField(
                modifier = Modifier.padding(start = 20.dp, bottom = 20.dp).height(250.dp).width(350.dp),
                value = data,
                onValueChange = { txt ->
                    data = txt
                }
            )
            Button(
                modifier = Modifier.padding(start = 20.dp),
                onClick = {
                    try{
                        val datajson = Json.parseToJsonElement(data) as JsonObject
                        val json = buildJsonObject {
                            put("id",clientDetailsViewModel.client?.carId)
                            put("schema",schema)
                            putJsonObject("data") {
                                datajson.forEach { (key, value) ->
                                    put(key, value)
                                }
                            }
                        }
                        val msg = MqttMessage()
                        msg.qos = 0
                        msg.isRetained = true
                        msg.payload = json.toString().toByteArray()
                        clientDetailsViewModel.client?.mqttClient?.publish("measurementz",msg)
                    } catch (e: Exception){
                        errorMessage = "An error occurred: ${e.message}"
                    }
                },
                enabled = !(clientDetailsViewModel.client?.carId.isNullOrEmpty()) && (data.isNotEmpty())
            ){ Text("Send") }
            errorMessage?.let { message ->
                ErrorDialog(message = message, onClose = { errorMessage = null })
            }
        }
    }
}

@Composable
fun autoreportingview(clientDetailsViewModel: ClientDetailsViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceAround
    ) {
        /*Column(
            modifier = Modifier.padding(16.dp).weight(1.0f),
            horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier.padding(16.dp)) { Text("Status:"); Text(clientDetailsViewModel.client?.engineStatus?.state.toString()) }
            Row(modifier = Modifier.padding(16.dp)) {
                Text("Curr. speed:"); Text(clientDetailsViewModel.client?.currentSpeed!!.value.toString()); Text(
                clientDetailsViewModel.client?.currentSpeed!!.unit.name
            )
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Text("Curr. location:")
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.padding(16.dp)) { Text("long.: ${clientDetailsViewModel.client?.currentlocation?.longitude}°,") }
                    Row(modifier = Modifier.padding(16.dp)) { Text("lat.: ${clientDetailsViewModel.client?.currentlocation?.latitude}°") }
                }
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Text("Temperature:")
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.padding(16.dp)) { Text("engine: ${clientDetailsViewModel.client?.temperature?.engine} ${clientDetailsViewModel.client?.tempUnit},") }
                    Row(modifier = Modifier.padding(16.dp)) { Text("int.: ${clientDetailsViewModel.client?.temperature?.internal} ${clientDetailsViewModel.client?.tempUnit},") }
                    Row(modifier = Modifier.padding(16.dp)) { Text("ext.: ${clientDetailsViewModel.client?.temperature?.external} ${clientDetailsViewModel.client?.tempUnit}") }
                }
            }
            Row(modifier = Modifier.padding(16.dp)) {
                Text("Tire pressure (in ${clientDetailsViewModel.client?.pressureUnit}):")
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(clientDetailsViewModel.client?.tirePressure?.front_left.toString())
                        Text(
                            modifier = Modifier.padding(start = 15.dp),
                            text = clientDetailsViewModel.client?.tirePressure?.front_right.toString()
                        )
                    }
                    Row(modifier = Modifier.padding(16.dp)) {
                        Text(clientDetailsViewModel.client?.tirePressure?.back_left.toString())
                        Text(
                            modifier = Modifier.padding(start = 15.dp),
                            text = clientDetailsViewModel.client?.tirePressure?.back_right.toString()
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.padding(16.dp).weight(1.0f),
            horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier.padding(16.dp)) { Text("Odometer:"); Text(" ${clientDetailsViewModel.client?.odometer?.value} ${clientDetailsViewModel.client?.distanceUnit}") }
            Row(modifier = Modifier.padding(16.dp)) { Text("Fuel level:"); Text(" ${clientDetailsViewModel.client?.fuel?.percentage}%") }
            Row(modifier = Modifier.padding(16.dp)) { Text("Engine oil level:"); Text(" ${clientDetailsViewModel.client?.oil?.level}") }
            Row(modifier = Modifier.padding(16.dp)) { Text("Windshield cleaner level:"); Text(" ${clientDetailsViewModel.client?.windshieldCleaner?.level}") }
            Row(modifier = Modifier.padding(16.dp)) { Text("Battery charge:"); Text(" ${clientDetailsViewModel.client?.batteryStatus?.percentage}%") }

        }*/
    }
}
