import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import model.Client
import view.ErrorDialog
import view.detailsView
import viewmodel.ClientDetailsViewModel
import viewmodel.NewClientViewModel


var clientlist by mutableStateOf(SnapshotStateList<Client>())
var serverURI by mutableStateOf("")
var serverPort by mutableStateOf("")
var ipaddr_regex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}\$".toRegex()
var domain_regex = "^(?!:\\/\\/)([a-zA-Z0-9-]{1,63}\\.?){1,}([a-zA-Z]{2,63})\$\n".toRegex()
var port_regex =
    "^((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9]{2})|(6[0-4][0-9]{3})|([1-5][0-9]{4})|([0-5]{0,5})|([0-9]{1,4}))\$".toRegex()

@Composable
fun clientWidget(item: Client, clientDetailsViewModel: ClientDetailsViewModel) {
    Card(
        modifier = Modifier.padding(20.dp)
            .clickable {
                clientDetailsViewModel.client = item
                clientDetailsViewModel.visible = true
            },
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp,

        ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = item.name
                )
                IconButton(
                    onClick = {
                        clientlist.remove(item)
                        item.mqttDisconnect()
                    }
                ) {
                    Icon(Icons.Default.Delete, contentDescription = null)
                }
            }
            Text(modifier = Modifier.padding(10.dp), fontSize = 10.sp, fontWeight = FontWeight.Light, text = item.uuid)
        }
    }
}

@Composable
@Preview
fun App() {
    var newClientViewModel = NewClientViewModel()
    var clientDetailsViewModel = ClientDetailsViewModel()

    MaterialTheme {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        modifier = Modifier.width(250.dp).padding(start = 20.dp, end = 20.dp),
                        painter = painterResource("fleetbrother-logo_small.png"),
                        contentDescription = "logo"
                    )
                    Text(modifier = Modifier.padding(start = 20.dp), text = "OBU Simulator", color = Color.Black)
                }

                TextField(
                    modifier = Modifier.padding(16.dp).weight(3.0f),
                    value = serverURI,
                    placeholder = { Text("Server address") },
                    onValueChange = { newText ->
                        serverURI = newText
                    }
                )
                TextField(
                    modifier = Modifier.padding(16.dp).weight(2.0f),
                    value = serverPort,
                    placeholder = { Text("Server port") },
                    onValueChange = { newText ->
                        serverPort = newText
                    }
                )
                Button(
                    modifier = Modifier.padding(16.dp).weight(1.0f),
                    onClick = {
                        newClientViewModel.isdialogvisible = true
                    },
                    enabled = (serverURI.isNotEmpty() && (serverURI.matches(ipaddr_regex) || serverURI.matches(
                        domain_regex
                    ))) /*&& (serverPort.isNotEmpty() && serverPort.matches(port_regex))*/,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 250.dp)
            ) {
                items(clientlist.size) { idx ->
                    clientWidget(clientlist[idx], clientDetailsViewModel)
                }
            }
            if (newClientViewModel.isdialogvisible) {
                newClientDialog(newClientViewModel)
            }
        }

        AnimatedVisibility(
            visible = clientDetailsViewModel.visible,
            enter = slideInHorizontally()
        ) {
            detailsView(clientDetailsViewModel)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun newClientDialog(newClientViewModel: NewClientViewModel) {
    newClientViewModel.clientname = ""
    newClientViewModel.clientLicensePlate = ""
    newClientViewModel.clientVin = ""
    var errorMessage by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        title = { Text("Create new client") },
        text = {
            Row {
                TextField(
                    modifier = Modifier.padding(10.dp),
                    value = newClientViewModel.clientname,
                    placeholder = { Text("Name") },
                    onValueChange = { newText ->
                        newClientViewModel.clientname = newText
                    }
                )
                TextField(
                    modifier = Modifier.padding(10.dp),
                    value = newClientViewModel.clientLicensePlate,
                    placeholder = { Text("License plate") },
                    onValueChange = { newText ->
                        newClientViewModel.clientLicensePlate = newText
                    }
                )
                TextField(
                    modifier = Modifier.padding(10.dp),
                    value = newClientViewModel.clientVin,
                    placeholder = { Text("VIN") },
                    onValueChange = { newText ->
                        newClientViewModel.clientVin = newText
                    }
                )
            }

        },
        onDismissRequest = {},
        confirmButton = {
            Button(
                onClick = {
                    //GlobalScope.launch (Dispatchers.Swing){
                    try {
                        if (newClientViewModel.clientname.isNotEmpty() &&
                            newClientViewModel.clientLicensePlate.isNotEmpty() &&
                            newClientViewModel.clientVin.isNotEmpty()
                        ) {
                            val newclient = Client(
                                name = newClientViewModel.clientname,
                                licensePlate = newClientViewModel.clientLicensePlate,
                                vin = newClientViewModel.clientVin,
                                serverURI = "tcp://${serverURI}:${serverPort}"
                            )

                            clientlist.add(newclient)
                            newClientViewModel.isdialogvisible = false
                        }
                    } catch (e: Exception) {
                        errorMessage = "An error occurred: ${e.message}"
                    }
                    //}
                }
            ) { Text("Create") }
        },
        dismissButton = { Button(onClick = { newClientViewModel.isdialogvisible = false }) { Text("Cancel") } }
    )
    errorMessage?.let { message ->
        ErrorDialog(message = message, onClose = { errorMessage = null })
    }
}

fun main() = application {
    Window(
        icon = painterResource("fleet_small.png"),
        title = "FleetBrother Simulator",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
