package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class NewClientViewModel{
    var isdialogvisible by mutableStateOf(false)
    var clientname by mutableStateOf("")
}