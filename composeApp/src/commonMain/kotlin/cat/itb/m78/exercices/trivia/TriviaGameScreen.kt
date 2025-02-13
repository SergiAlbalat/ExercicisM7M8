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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TriviaGameScreenApp(navigateToScoreScreen: (Int) -> Unit){
    val viewModel = viewModel{TriviaGameScreenViewModel()}
    TriviaSceen2View(
        viewModel.questionCounter.value,
        navigateToScoreScreen,
        viewModel.currentQuestion.value,
        viewModel::answer,
        viewModel.settings,
    )
}

@Composable
fun TriviaSceen2View(
    roundNum: Int,
    navigateToScoreScreen: (Int)->Unit,
    currentQuestion: Question,
    answer: (Int, (Int)->Unit)->Unit,
    settings: TrivialSettings,
){

    @Composable
    fun printAnswer(buttonNum: Int){
        Button(onClick = {answer(buttonNum, navigateToScoreScreen)}, Modifier.padding(10.dp)){
            Text(currentQuestion.answer[buttonNum-1])
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
        Text(currentQuestion.question, fontSize = 2.em, textAlign = TextAlign.Center)
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
    }
}