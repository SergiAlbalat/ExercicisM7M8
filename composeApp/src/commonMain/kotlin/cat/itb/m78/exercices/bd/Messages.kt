package cat.itb.m78.exercices.bd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagesViewModel : ViewModel(){
    private val messagesQueries = database.messagesQueries
    val messages = messagesQueries.selectAll().executeAsList()
    val userInput = mutableStateOf("")

    fun userInputChange(it: String){
        userInput.value = it
    }

    fun saveMessage(){
        if(userInput.value != ""){
            viewModelScope.launch {
                withContext(Dispatchers.Default){
                    database.messagesQueries.insert(userInput.value)
                    userInput.value = ""
                }
            }
        }
    }
}

@Composable
fun MessagesApp(){
    val viewModel = viewModel{ MessagesViewModel() }
    MessagesView(
        viewModel.messages,
        viewModel.userInput.value,
        viewModel::userInputChange,
        viewModel::saveMessage
    )
}

@Composable
fun MessagesView(
    messages: List<String>,
    userInput: String,
    userInputChange: (String)->Unit,
    saveMessage: ()->Unit
){
    Column {
        LazyColumn {
            items(messages){ message ->
                Text(message)
            }
        }
        Text("Message:")
        TextField(
            userInput,
            onValueChange = userInputChange
        )
        Button(onClick = saveMessage){
            Text("Save")
        }
    }
}