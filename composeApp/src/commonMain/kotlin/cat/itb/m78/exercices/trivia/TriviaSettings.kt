package cat.itb.m78.exercices.trivia

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TriviaSettingsApp(navigateToScreen1: () -> Unit){
    val viewModel = viewModel{TriviaSettingsViewModel()}
    TriviaSettingsView(
        viewModel.difficulty.value,
        viewModel.rounds.value,
        viewModel::roundToFive,
        viewModel::roundToTen,
        viewModel::roundToFifteen,
        viewModel::easyDifficulty,
        viewModel::normalDifficulty,
        viewModel::hardDifficulty,
        navigateToScreen1,
        viewModel::saveExit
    )
}

@Composable
fun TriviaSettingsView(
    difficulty: Int,
    round: Int,
    roundToFive: ()->Unit,
    roundToTen: ()->Unit,
    roundToFifteen: ()->Unit,
    easyDifficulty: ()->Unit,
    normalDifficulty: ()->Unit,
    hardDifficulty: ()->Unit,
    navigateToScreen1: ()->Unit,
    saveExit: (()->Unit)->Unit
){
    @Composable
    fun CreateRadioButtonRound(num: Int, function: ()->Unit){
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = (round == num),
                onClick = function
            )
            Text(num.toString())
        }
    }
    @Composable
    fun CreateRadioButtonDifficulty(num: Int, function: ()->Unit, text: String){
        Row(verticalAlignment = Alignment.CenterVertically){
            RadioButton(
                selected = (difficulty == num),
                onClick = function
            )
            Text(text)
        }
    }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Difficulty")
            Column {
                CreateRadioButtonDifficulty(1, easyDifficulty, "Easy")
                CreateRadioButtonDifficulty(2, normalDifficulty, "Normal")
                CreateRadioButtonDifficulty(3, hardDifficulty, "Hard")
            }
        }
        Spacer(Modifier.height(30.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Rounds")
            Column {
                CreateRadioButtonRound(5, roundToFive)
                CreateRadioButtonRound(10, roundToTen)
                CreateRadioButtonRound(15, roundToFifteen)
            }
        }
        Spacer(Modifier.height(40.dp))
        Button(onClick = { saveExit(navigateToScreen1) }){
            Text("Return to menu")
        }
    }
}