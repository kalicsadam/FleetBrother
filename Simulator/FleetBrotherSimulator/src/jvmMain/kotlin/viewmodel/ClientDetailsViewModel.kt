package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import model.Client

class ClientDetailsViewModel {
    var visible by mutableStateOf(false)
    //var client by mutableStateOf(Client("",""))
}