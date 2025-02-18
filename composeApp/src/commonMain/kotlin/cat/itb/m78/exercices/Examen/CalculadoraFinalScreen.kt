package cat.itb.m78.exercices.Examen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Calculator_icon
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun CalculadoraFinalScreen(result: Int){
    Column(Modifier.fillMaxSize().background(Color.Yellow), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("The final result is")
        Text(result.toString(), fontSize = 3.em)
        Image(
            painter = painterResource(Res.drawable.Calculator_icon),
            contentDescription = "",
            Modifier.clip(CircleShape).size(250.dp)
        )
    }
}