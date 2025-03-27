package cat.itb.m78.exercices.bd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Messages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MessagesViewModel : ViewModel(){
    private val messagesQueries = database.messagesQueries
    private val languagesQueries = database.languagesQueries
    val messages = mutableStateOf(messagesQueries.selectAll().executeAsList())
    val language = mutableStateOf<Long>(1)
    val userInputText = mutableStateOf("")
    val userInputLanguage = mutableStateOf("")
    val expanded = mutableStateOf(false)
    val languages = languagesQueries.selectNames().executeAsList()

    fun userInputChange(it: String){
        userInputText.value = it
    }

    fun userLanguageChange(it: String){
        userInputLanguage.value = it
    }

    fun openMenu(){
        expanded.value = !expanded.value
    }

    fun closeMenu(){
        expanded.value = false
    }

    fun setLanguage(languageName: String){
        when(languageName){
            "English" -> language.value = 1
            "Espanol" -> language.value = 2
            "Catala" -> language.value = 3
        }
    }

    fun saveMessage(){
        if(userInputText.value != ""){
            viewModelScope.launch {
                withContext(Dispatchers.Default){
                    database.messagesQueries.insert(userInputText.value, language.value)
                    messages.value = messagesQueries.selectAll().executeAsList()
                    userInputText.value = ""
                }
            }
        }
    }
}

@Composable
fun MessagesApp(){
    val viewModel = viewModel{ MessagesViewModel() }
    MessagesView(
        viewModel.messages.value,
        viewModel.userInputText.value,
        viewModel.userInputLanguage.value,
        viewModel::userInputChange,
        viewModel::userLanguageChange,
        viewModel::saveMessage,
        viewModel.expanded.value,
        viewModel::openMenu,
        viewModel::closeMenu,
        viewModel.languages,
        viewModel::setLanguage
    )
}

@Composable
fun MessagesView(
    messages: List<Messages>,
    userInputText: String,
    userInputLanguage: String,
    userInputChange: (String)->Unit,
    userLanguageChange: (String)->Unit,
    saveMessage: ()->Unit,
    expanded: Boolean,
    openMenu: ()->Unit,
    closeMenu: ()->Unit,
    languages: List<String>,
    setLanguage: (String)->Unit
){
    Column {
        LazyColumn {
            items(messages){ message ->
                Row {
                    Text(message.message)

                }
            }
        }
        Text("Message:")
        Row {
            TextField(
                userInputText,
                onValueChange = userInputChange,
                label = { Text("Text") },
            )
            Spacer(Modifier.width(20.dp))
            Button(onClick = openMenu) {
                Text("Language")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = closeMenu
            ) {
                for(i in languages){
                    DropdownMenuItem(
                        text = { Text(i) },
                        onClick = {setLanguage(i)}
                    )
                }
            }
        }
        Button(onClick = saveMessage){
            Text("Save")
        }
    }
}