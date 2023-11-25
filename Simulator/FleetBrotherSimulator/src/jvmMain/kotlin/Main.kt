import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.desktop.ui.tooling.preview.Preview
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import model.Client
import view.detailsView
import viewmodel.ClientDetailsViewModel
import viewmodel.LoadingViewModel
import viewmodel.NewClientViewModel


var clientlist by mutableStateOf(SnapshotStateList<Client>())
var serverURI by mutableStateOf("192.168.1.200")
var serverPort by mutableStateOf("1883")
var ipaddr_regex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}\$".toRegex()
var domain_regex = "^(?!:\\/\\/)([a-zA-Z0-9-]{1,63}\\.?){1,}([a-zA-Z]{2,63})\$\n".toRegex()
var port_regex = "^((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9]{2})|(6[0-4][0-9]{3})|([1-5][0-9]{4})|([0-5]{0,5})|([0-9]{1,4}))\$".toRegex()

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
            Text(modifier = Modifier.padding(10.dp), fontSize = 10.sp, fontWeight = FontWeight.Light, text = item.id)
        }
    }
}

@Composable
@Preview
fun App() {
    var newClientViewModel = NewClientViewModel()
    var loadingViewModel = LoadingViewModel()
    var clientDetailsViewModel = ClientDetailsViewModel()

    MaterialTheme {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(16.dp).weight(3.0f),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    text = "Clients"
                )
                TextField(
                    modifier = Modifier.padding(16.dp).weight(3.0f),
                    value = serverURI,
                    placeholder = {Text("Server address")},
                    onValueChange = { newText ->
                        serverURI = newText
                    }
                )
                TextField(
                    modifier = Modifier.padding(16.dp).weight(2.0f),
                    value = serverPort,
                    placeholder = {Text("Server port")},
                    onValueChange = { newText ->
                        serverPort = newText
                    }
                )
                Button(
                    modifier = Modifier.padding(16.dp).weight(1.0f),
                    onClick = {
                        newClientViewModel.isdialogvisible = true
                    },
                    enabled = (serverURI.isNotEmpty() && (serverURI.matches(ipaddr_regex) || serverURI.matches(domain_regex))) /*&& (serverPort.isNotEmpty() && serverPort.matches(port_regex))*/,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp)
            ) {
                items(clientlist.size) { idx ->
                    clientWidget(clientlist[idx],clientDetailsViewModel)
                }
            }
            if (newClientViewModel.isdialogvisible) {
                newClientDialog(newClientViewModel, loadingViewModel)
            }
        }

        AnimatedVisibility(
            visible = clientDetailsViewModel.visible,
            enter = slideInHorizontally()
        ) {
            detailsView(clientDetailsViewModel)
        }

        AnimatedVisibility(
            visible = loadingViewModel.visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 200)),
            exit = fadeOut(animationSpec = tween(durationMillis = 200))
        ) {
            Surface(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.size(100.dp).padding(20.dp)
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun newClientDialog(newClientViewModel: NewClientViewModel, loadingViewModel: LoadingViewModel) {
    newClientViewModel.clientname = ""
    AlertDialog(
        title = { Text("Create new client") },
        text = {
            TextField(
                modifier = Modifier.padding(10.dp),
                value = newClientViewModel.clientname,
                onValueChange = { newText ->
                    newClientViewModel.clientname = newText
                }
            )
        },
        onDismissRequest = {},
        confirmButton = {
            Button(
                onClick = {
                    GlobalScope.launch (Dispatchers.Swing){
                        loadingViewModel.visible = true
                        if (newClientViewModel.clientname.isNotEmpty()) {
                            val newclient = Client(name = newClientViewModel.clientname, serverURI = "tcp://${serverURI}:${serverPort}")
                            clientlist.add(newclient)
                            loadingViewModel.visible = false
                            newClientViewModel.isdialogvisible = false
                        }
                    }
                }
            ) { Text("Create") }
        },
        dismissButton = { Button(onClick = { newClientViewModel.isdialogvisible = false }) { Text("Cancel") } }
    )
}

fun main() = application {
    Window(
        title = "FleetBrother Client Simulator",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
