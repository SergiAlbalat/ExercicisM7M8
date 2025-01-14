package cat.itb.m78.exercices.State

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.tapestry
import m78exercices.composeapp.generated.resources.title
import org.jetbrains.compose.resources.painterResource

@Composable
fun DiceRoller(){
    Image(
        painter = painterResource(Res.drawable.tapestry),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.title),
            contentDescription = null
        )
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()){
            Text("Roll the dice")
        }
    }
}