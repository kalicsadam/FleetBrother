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
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp).verticalScroll(state = ScrollState(0))
        ) {

            IconButton(
                onClick = { clientDetailsViewModel.visible = !clientDetailsViewModel.visible }
            ) { Icon(imageVector = Icons.Default.Close, contentDescription = null) }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(){
                    Text(modifier = Modifier.padding(20.dp), fontSize = 30.sp, text = clientDetailsViewModel.client!!.name)
                    Text(modifier = Modifier.padding(20.dp), fontSize = 15.sp, text = clientDetailsViewModel.client!!.id)
                }

                //alertBox(/*alert*/clientDetailsViewModel)
            }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
            ){
                Column (
                    modifier = Modifier.weight(1.0f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row{ Text("Status:"); Text(clientDetailsViewModel.client?.engineStatus!!.state.name) }
                    Row{ Text("Curr. speed:"); Text(clientDetailsViewModel.client?.currentSpeed!!.value.toString()); Text(clientDetailsViewModel.client?.currentSpeed!!.unit.name) }
                    Row{
                        Text("Curr. location:")
                        Column {
                            Row{ Text("long.: ${clientDetailsViewModel.client?.currentlocation?.longitude}°,") }
                            Row{ Text("lat.: ${clientDetailsViewModel.client?.currentlocation?.latitude}°") }
                        }
                    }
                    Row{
                        Text("Temperature:")
                        Column {
                            Row{ Text("int.: ${clientDetailsViewModel.client?.temperature?.internal} ${clientDetailsViewModel.client?.tempUnit},") }
                            Row{ Text("ext.: ${clientDetailsViewModel.client?.temperature?.external} ${clientDetailsViewModel.client?.tempUnit}") }
                        }
                    }
                }
                Column (
                    modifier = Modifier.weight(1.0f),
                    horizontalAlignment = Alignment.Start
                ){

                }

            }
        }
    }
}
