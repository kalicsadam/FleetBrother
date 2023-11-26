package view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viewmodel.ClientDetailsViewModel


/*@Composable
fun alertBox(clientDetailsViewModel: ClientDetailsViewModel) {
    Card(
        backgroundColor =
        when (clientDetailsViewModel.client?.latestAlert?.alertPriority) {
            AlertPriority.INFO -> Color( 173, 216, 230)
            AlertPriority.WARNING -> Color(255,165,0)
            AlertPriority.IMMEDIATE_ACTION -> Color.Red
            else -> Color.Unspecified
        },
        contentColor = Color.White,
        elevation = 10.dp,
        shape = RoundedCornerShape(10)
    ) {
        when (clientDetailsViewModel.client?.latestAlert?.alertPriority) {
            AlertPriority.INFO -> {
                Column() {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = "Information from the operator",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp), text = clientDetailsViewModel.client?.latestAlert!!.description)
                }
            }

            AlertPriority.WARNING -> {
                Column {
                    Text(modifier = Modifier.padding(20.dp),
                        text = "Warning!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp), text = clientDetailsViewModel.client?.latestAlert!!.description)
                }
            }

            AlertPriority.IMMEDIATE_ACTION -> {
                Column {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = "Immediate action required!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(modifier = Modifier.padding(start = 20.dp, bottom = 20.dp), text = clientDetailsViewModel.client?.latestAlert!!.description)
                }
            }

            else -> {}
        }

    }
}*/

@Composable
fun detailsView(clientDetailsViewModel: ClientDetailsViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 20.dp
    ) {
        var livez by remember { mutableStateOf(clientDetailsViewModel.client?.livezPeriod.toString()) }
        var param by remember { mutableStateOf( clientDetailsViewModel.client?.paramPeriod.toString()) }
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp).verticalScroll(state = ScrollState(0))
        ) {

            IconButton(
                onClick = { clientDetailsViewModel.visible = !clientDetailsViewModel.visible }
            ) { Icon(imageVector = Icons.Default.Close, contentDescription = null) }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1.0f)){
                    Text(modifier = Modifier.padding(20.dp), fontSize = 30.sp, text = clientDetailsViewModel.client!!.name)
                    Text(modifier = Modifier.padding(20.dp), fontSize = 15.sp, text = clientDetailsViewModel.client!!.id)
                }
                Column {
                    Text("Livez period:")
                    Row{
                        TextField(
                            modifier = Modifier.width(100.dp),
                            value = livez,
                            onValueChange = { txt ->
                                livez = txt
                            }
                        )
                        Button(
                            modifier = Modifier.padding(start = 15.dp,end=15.dp),
                            onClick = {
                                clientDetailsViewModel.client?.livezPeriod = livez.toLong()
                            },
                            enabled = ((livez.isNotEmpty() && livez.matches("^[0-9]*\$".toRegex())))
                        ){ Text("Set") }
                    }
                }

                Column {
                    Text("Measurementz period:")
                    Row{
                        TextField(
                            modifier = Modifier.width(100.dp),
                            value = param,
                            onValueChange = { txt ->
                                param = txt
                            }
                        )
                        Button(
                            modifier = Modifier.padding(start = 15.dp,end=15.dp),
                            onClick = {
                                clientDetailsViewModel.client?.paramPeriod = param.toLong()
                            },
                            enabled = ((param.isNotEmpty() && param.matches("^[0-9]*\$".toRegex())))
                        ){ Text("Set") }
                    }
                }

                //alertBox(/*alert*/clientDetailsViewModel)
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceAround
            ){
                Column (
                    modifier = Modifier.padding(16.dp).weight(1.0f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(modifier = Modifier.padding(16.dp)){ Text("Status:"); Text(clientDetailsViewModel.client?.engineStatus!!.state.name) }
                    Row(modifier = Modifier.padding(16.dp)){ Text("Curr. speed:"); Text(clientDetailsViewModel.client?.currentSpeed!!.value.toString()); Text(clientDetailsViewModel.client?.currentSpeed!!.unit.name) }
                    Row(modifier = Modifier.padding(16.dp)){
                        Text("Curr. location:")
                        Column (modifier = Modifier.padding(16.dp)){
                            Row(modifier = Modifier.padding(16.dp)){ Text("long.: ${clientDetailsViewModel.client?.currentlocation?.longitude}°,") }
                            Row(modifier = Modifier.padding(16.dp)){ Text("lat.: ${clientDetailsViewModel.client?.currentlocation?.latitude}°") }
                        }
                    }
                    Row(modifier = Modifier.padding(16.dp)){
                        Text("Temperature:")
                        Column (modifier = Modifier.padding(16.dp)){
                            Row(modifier = Modifier.padding(16.dp)){ Text("int.: ${clientDetailsViewModel.client?.temperature?.internal} ${clientDetailsViewModel.client?.tempUnit},") }
                            Row(modifier = Modifier.padding(16.dp)){ Text("ext.: ${clientDetailsViewModel.client?.temperature?.external} ${clientDetailsViewModel.client?.tempUnit}") }
                        }
                    }
                    Row(modifier = Modifier.padding(16.dp)){
                        Text("Tire pressure (in ${clientDetailsViewModel.client?.pressureUnit}):")
                        Column (modifier = Modifier.padding(16.dp)){
                            Row(modifier = Modifier.padding(16.dp)){
                                Text(clientDetailsViewModel.client?.tirePressure?.front_left.toString())
                                Text(modifier = Modifier.padding(start = 15.dp),text=clientDetailsViewModel.client?.tirePressure?.front_right.toString().format("#.##"))
                            }
                            Row(modifier = Modifier.padding(16.dp)){
                                Text(clientDetailsViewModel.client?.tirePressure?.back_left.toString())
                                Text(modifier = Modifier.padding(start = 15.dp),text=clientDetailsViewModel.client?.tirePressure?.back_right.toString().format("#.##"))
                            }
                        }
                    }
                }
                Column (
                    modifier = Modifier.padding(16.dp).weight(1.0f),
                    horizontalAlignment = Alignment.Start
                ){
                    Row(modifier = Modifier.padding(16.dp)){ Text("Odometer:"); Text(" ${clientDetailsViewModel.client?.odometer?.value} ${clientDetailsViewModel.client?.distanceUnit}") }
                    Row(modifier = Modifier.padding(16.dp)){ Text("Fuel level:"); Text(" ${clientDetailsViewModel.client?.fuel?.percentage}%") }
                    Row(modifier = Modifier.padding(16.dp)){ Text("Engine oil level:"); Text(" ${clientDetailsViewModel.client?.oil?.level}") }
                    Row(modifier = Modifier.padding(16.dp)){ Text("Windshield cleaner level:"); Text(" ${clientDetailsViewModel.client?.windshieldCleaner?.level}") }
                    Row(modifier = Modifier.padding(16.dp)){ Text("Battery charge:"); Text(" ${clientDetailsViewModel.client?.batteryStatus?.percentage}%") }

                }

            }
        }
    }
}
