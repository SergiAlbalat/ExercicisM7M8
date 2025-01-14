package cat.itb.m78.exercices.State

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun GoodMorningAndNight(){
    val greeting = remember { mutableStateOf("Good?!") }
    Column{
        Text(greeting.value)
        Button(onClick = {
            greeting.value = "Good Morning"
        }){
            Text("Morning")
        }
        Button(onClick = {
            greeting.value = "Good Night"
        }){
            Text("Night")
        }
    }
}