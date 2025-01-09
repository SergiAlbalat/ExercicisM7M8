package cat.itb.m78.exercices.Stateless

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Welcome(){
    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
        Text("Welcome", fontSize = 2.em)
        Text("Start learning now")
        Spacer(Modifier.height(70.dp))
        Button(onClick = {}){
            Text("Login")
        }
        Button(onClick = {}){
            Text("Register")
        }
    }
}