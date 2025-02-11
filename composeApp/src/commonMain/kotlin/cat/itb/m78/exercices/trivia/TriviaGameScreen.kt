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
        viewModel.score.value,
        keys,
        answers,
        questions,
        viewModel::scoreUp,
        viewModel::printQuestion,
        viewModel.currentQuestion.value
    )
}

@Composable
fun TriviaSceen2View(
    roundNum: Int,
    navigateToScoreScreen: (Int)->Unit,
    score: Int,
    keys: List<Int>,
    answers: List<List<String>>,
    questions: List<String>,
    scoreUp: ()->Unit,
    printQuestion: ()->String,
    currentQuestion: Question
){

    @Composable
    fun printAnswer(buttonNum: Int){
        if(roundNum == questionMax+1) {
            navigateToScoreScreen(score)
        }else if(difficulty == 3){
            if(keys[roundNum-1] == buttonNum){
                Button(onClick = scoreUp, Modifier.padding(10.dp)){
                    Text(answers[roundNum-1][buttonNum-1])
                }
            }else{
                Button(onClick = {navigateToScoreScreen(score)}, Modifier.padding(10.dp)){
                    Text(answers[roundNum-1][buttonNum-1])
                }
            }
        }else if(difficulty == 2){
            if(keys[roundNum-1] == 4){
                Text(newKey.toString())
                if(newKey == buttonNum){
                    Button(onClick = scoreUp, Modifier.padding(10.dp)){
                        Text(answers[roundNum-1][keys[roundNum-1]])
                    }
                }else{
                    Button(onClick = {navigateToScoreScreen(score)}, Modifier.padding(10.dp)){
                        Text(answers[roundNum-1][buttonNum-1])
                    }
                }
            }else{
                Text(newKey.toString())
                if(keys[roundNum-1] == buttonNum){
                    Button(onClick = scoreUp, Modifier.padding(10.dp)){
                        Text(answers[roundNum-1][buttonNum-1])
                    }
                }else{
                    Button(onClick = {navigateToScoreScreen(score)}, Modifier.padding(10.dp)){
                        Text(answers[roundNum-1][buttonNum-1])
                    }
                }
            }
        }

    }

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Row{
            Text("Round ")
            Text(roundNum.toString())
            Text("/")
            Text(questionMax.toString())
        }
        Spacer(Modifier.height(100.dp))
        Text(printQuestion(), fontSize = 2.em, textAlign = TextAlign.Center)
        Spacer(Modifier.height(30.dp))
        if(difficulty == 3){
            Row {
                printAnswer(1)
                printAnswer(2)
            }
            Row {
                printAnswer(3)
                printAnswer(4)
            }
        }else if(difficulty == 2){
            Row {
                printAnswer(1)
                printAnswer(2)
            }
            printAnswer(3)
        }
    }
}