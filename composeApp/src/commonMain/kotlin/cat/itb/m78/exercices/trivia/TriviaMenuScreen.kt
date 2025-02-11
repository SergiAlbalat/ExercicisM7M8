package cat.itb.m78.exercices.trivia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.dbdicon
import org.jetbrains.compose.resources.painterResource

@Composable
fun TriviaMenuScreenView(navigateToGameScreen: ()->Unit, navigateToSettings: ()->Unit){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(Res.drawable.dbdicon),
            contentDescription = null,
            modifier = Modifier.clip(CircleShape)
        )
        Spacer(Modifier.height(50.dp))
        Button(onClick = navigateToGameScreen){
            Text("New Game")
        }
        Button(onClick = navigateToSettings){
            Text("Settings")
        }
    }
}