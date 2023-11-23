package view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.ClientDetailsViewModel


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
        }
    }
}
