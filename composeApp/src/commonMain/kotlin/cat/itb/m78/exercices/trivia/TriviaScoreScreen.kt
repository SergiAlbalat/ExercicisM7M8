package cat.itb.m78.exercices.trivia

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
fun TriviaScoreScreenView(
    score: Int,
    navigateToScreen1: ()->Unit
){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Your Score:", fontSize = 3.em)
        Text(score.toString(), fontSize = 2.em)
        Spacer(Modifier.height(30.dp))
        Button(onClick = navigateToScreen1){
            Text("Return to menu")
        }
    }
}