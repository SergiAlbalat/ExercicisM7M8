package cat.itb.m78.exercices.State

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SayHello(){
    var text by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        TextField(text,
            label = { Text("Name") },
            onValueChange = {
                text = it
            }
        )
        Button(onClick = {
            showDialog = true
        }){
            Text("SayHello")
        }
        if(showDialog)
            AlertDialog(
                onDismissRequest={showDialog = false},
                confirmButton={},
                text = {Text("HELLO $text")}
            )
    }
}