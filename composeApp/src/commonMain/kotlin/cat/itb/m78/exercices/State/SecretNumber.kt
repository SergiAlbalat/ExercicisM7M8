package cat.itb.m78.exercices.State

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import kotlin.random.Random

@Composable
fun SecretNumber(){
    val number by remember { mutableStateOf(Random.nextInt(0, 100)) }
    val gran = "El número que busques és més gran"
    val petit = "El número que busques és més petit"
    val guanyar = "Has encertat!"
    val puntuacio = "Intents:"
    var text by remember { mutableStateOf("") }
    var intents by remember { mutableStateOf(0) }
    var result by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Text("Endevina el número secret")
        TextField(
            text,
            onValueChange = {
                text = it
            }
        )
        Button(onClick = {
            if(text.toInt() == number){
                result = guanyar
            }else if(text.toInt() < number){
                result = gran
            }else if(text.toInt() > number){
                result = petit
            }
            intents++
        }){
            Text("Verificar")
        }
        Text("$puntuacio $intents")
        Text(result)
    }
}