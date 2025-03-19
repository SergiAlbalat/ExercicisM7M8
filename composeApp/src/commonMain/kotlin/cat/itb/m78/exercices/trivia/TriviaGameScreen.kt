package cat.itb.m78.exercices.trivia

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun TriviaGameScreenApp(navigateToScoreScreen: (Int) -> Unit){
    val viewModel = viewModel{TriviaGameScreenViewModel()}
    TriviaSceen2View(
        viewModel.questionCounter.value,
        navigateToScoreScreen,
        viewModel.currentQuestion.value,
        viewModel::answer,
        viewModel.settings,
        viewModel.score.value
    )
}

@Composable
fun TriviaSceen2View(
    roundNum: Int,
    navigateToScoreScreen: (Int)->Unit,
    currentQuestion: QuestionApi?,
    answer: (Int, (Int)->Unit)->Unit,
    settings: TrivialSettings,
    score: Int
){
    var timeLeft by remember { mutableStateOf(settings.time) }

    @Composable
    fun CountDown() {
        LaunchedEffect(timeLeft) {
            delay(1.seconds)
            timeLeft--
            if (timeLeft == 0) {
                navigateToScoreScreen(score)
            }
        }
        Text(timeLeft.toString(), fontSize = 2.em)
    }

    fun resetCountDown(){
        timeLeft = settings.time
    }

    @Composable
    fun printAnswer(buttonNum: Int){
        Button(onClick = {
            answer(buttonNum, navigateToScoreScreen)
            resetCountDown() }, Modifier.padding(15.dp)){
            if (currentQuestion != null) {
                Text(currentQuestion.answers[buttonNum-1])
            }
        }
    }

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Row{
            Text("Round ")
            Text(roundNum.toString())
            Text("/")
            Text(settings.rounds.toString())
        }
        Spacer(Modifier.height(100.dp))
        if (currentQuestion != null) {
            Text(currentQuestion.question, fontSize = 2.em, textAlign = TextAlign.Center)
        }
        Spacer(Modifier.height(30.dp))
        when (settings.difficulty) {
            3 -> {
                Row {
                    printAnswer(1)
                    printAnswer(2)
                }
                Row {
                    printAnswer(3)
                    printAnswer(4)
                }
            }
            2 -> {
                Row {
                    printAnswer(1)
                    printAnswer(2)
                }
                printAnswer(3)
            }
            1 -> {
                Row {
                    printAnswer(1)
                    printAnswer(2)
                }
            }
        }
        CountDown()
    }
}