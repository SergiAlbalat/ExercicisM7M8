package cat.itb.m78.exercices.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class RememberMyNameViewModel : ViewModel(){
    private val settings = Settings()
    val name = mutableStateOf<String?>("")

    val userInput = mutableStateOf("")
    init {
        name.value = settings["name"]
    }
    fun userInputChange(it: String){
        userInput.value = it
    }
    fun changeName(){
        settings["name"] = userInput.value
        name.value = userInput.value
    }
}

@Composable
fun RememberMyNameApp(){
    val viewModel = viewModel{RememberMyNameViewModel()}
    RememberMyNameView(
        viewModel.name.value,
        viewModel.userInput.value,
        viewModel::userInputChange,
        viewModel::changeName
    )
}

@Composable
fun RememberMyNameView(
    name: String?,
    userInput: String,
    userInputChange: (String)->Unit,
    changeName: ()->Unit
){
    Column {
        Text("Hello ${name.toString()}")
        TextField(
            userInput,
            onValueChange = userInputChange
        )
        Button(onClick = changeName){
            Text("Save")
        }
    }
}