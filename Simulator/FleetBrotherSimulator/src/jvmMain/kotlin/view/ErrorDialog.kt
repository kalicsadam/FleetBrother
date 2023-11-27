package view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ErrorDialog(message: String, onClose: () -> Unit) {
    Dialog(onCloseRequest = onClose) {
        // Dialog content
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Error")
            Spacer(modifier = Modifier.height(8.dp))
            Text(message)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onClose) {
                Text("OK")
            }
        }
    }
}