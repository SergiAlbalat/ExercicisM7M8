package cat.itb.m78.exercices.Trivia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.dbdicon
import org.jetbrains.compose.resources.painterResource

object TriviaScreens{
    @Serializable
    data object Screen1
    @Serializable
    data object Screen2
    @Serializable
    data class Screen3(val score: Int)
    @Serializable
    data object ScreenSettings
}

@Composable
fun TriviaNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TriviaScreens.Screen1){
        composable<TriviaScreens.Screen1>{
            TriviaScreen1View({navController.navigate(TriviaScreens.Screen2)}, {navController.navigate(TriviaScreens.ScreenSettings)})
        }
        composable<TriviaScreens.Screen2>{
            TriviaScreen2App({navController.navigate(TriviaScreens.Screen3(it))})
        }
        composable<TriviaScreens.Screen3> {
            TriviaScreen3View(it.toRoute<TriviaScreens.Screen3>().score, {navController.navigate(TriviaScreens.Screen1)})
        }
        composable<TriviaScreens.ScreenSettings> {
            TriviaSettingsApp({navController.navigate(TriviaScreens.Screen1)})
        }
    }
}

@Composable
fun TriviaScreen1View(navigateToScreen2: ()->Unit, navigateToSettings: ()->Unit){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(Res.drawable.dbdicon),
            contentDescription = null,
            modifier = Modifier.clip(CircleShape)
        )
        Spacer(Modifier.height(50.dp))
        Button(onClick = navigateToScreen2){
            Text("New Game")
        }
        Button(onClick = navigateToSettings){
            Text("Settings")
        }
    }
}

class TriviaScreen2ViewModel : ViewModel(){
    val score = mutableStateOf(0)
    val questionCounter = mutableStateOf(1)
    val questions = listOf(
        "When was Dead By Daylight released?",
        "Who is the main villain?",
        "How can the survivors escape from the trials?",
        "What uses the killer to make sacrifices?",
        "What's the meaning of NOED?",
        "From which videogame was Dead By Daylight inspired?",
        "How many diferent types of items are in the game?",
        "What are the four main categories of point for survivors?",
        "What are the four main categories of point for killers?",
        "What's the name of the killer called The Shape?"
    )
    val answers = listOf(
        listOf("2018", "2016", "2015", "2017"),
        listOf("The Entity", "The Trapper", "The Killer", "The Ace"),
        listOf("Powering the doors", "Powering the doors or powering the hatch", "Powering The hatch", "Powering the doors or using the hatch"),
        listOf("Altars", "Oferings", "Generators", "Hooks"),
        listOf("No One Escapes Death", "New Ongoing Endgame Duty", "Neptune Offshore Engineering Development", "No Observed Effect Dose"),
        listOf("Alan Wake", "Friday the 13th: The Videogame", "The Resident Evil Games", "None"),
        listOf("2", "5", "6", "4"),
        listOf("Reparing, Looping, Healing and Crafting", "Objectives, Healing, Breaking and Reparing", "Objectives, Boldness, Altruism and Survival", "Brutallity, Survival, Crafting and Toteming"),
        listOf("Deviousness, Brutallity, Hunter and Sacrifice", "Evilness, Killing, Hunter and Ofering", "Speed, Strenght, Perception and Madness", "Reparing, Healing, Looping, Toteming"),
        listOf("Jason Voorhees", "Danny Johnson", "Michael Myers", "Charles Lee Ray")
    )
    val keys = listOf(2, 1, 4, 4, 1, 1, 3, 3, 1, 3)
    fun scoreUp(){
        score.value += 100
        questionCounter.value++
    }
}

@Composable
fun TriviaScreen2App(navigateToScreen3: (Int) -> Unit){
    val viewModel = viewModel{TriviaScreen2ViewModel()}
    TriviaSceen2View(
        viewModel.questionCounter.value,
        navigateToScreen3,
        viewModel.score.value,
        viewModel.keys,
        viewModel.answers,
        viewModel.questions,
        viewModel::scoreUp
    )
}

@Composable
fun TriviaSceen2View(
    roundNum: Int,
    navigateToScreen3: (Int)->Unit,
    score: Int,
    keys: List<Int>,
    answers: List<List<String>>,
    questions: List<String>,
    scoreUp: ()->Unit
){
    @Composable
    fun printAnswer(buttonNum: Int){
        if(roundNum == 11) {
            navigateToScreen3(score)
        }else if(keys[roundNum-1] == buttonNum){
            Button(onClick = scoreUp, Modifier.padding(10.dp)){
                Text(answers[roundNum-1][buttonNum-1])
            }
        }else{
            Button(onClick = {navigateToScreen3(score)}, Modifier.padding(10.dp)){
                Text(answers[roundNum-1][buttonNum-1])
            }
        }
    }
    @Composable
    fun printQuestion(): String{
        if(roundNum!=11){
            return questions[roundNum-1]
        }else{
            return ""
        }
    }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Row{
            Text("Round ")
            Text(roundNum.toString())
            Text("/10")
        }
        Spacer(Modifier.height(100.dp))
        Text(printQuestion(), fontSize = 2.em, textAlign = TextAlign.Center)
        Spacer(Modifier.height(30.dp))
        Row {
            printAnswer(1)
            printAnswer(2)
        }
        Row {
            printAnswer(3)
            printAnswer(4)
        }
    }
}

@Composable
fun TriviaScreen3View(
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

class TriviaSettingsViewModel : ViewModel(){
    val difficulty = mutableStateOf(2)
    val rounds = mutableStateOf(10)
    fun easyDifficulty(){
        difficulty.value = 1
    }
    fun normalDifficulty(){
        difficulty.value = 2
    }
    fun hardDifficulty(){
        difficulty.value = 3
    }
    fun roundToFive(){
        rounds.value = 5
    }
    fun roundToTen(){
        rounds.value = 10
    }
    fun roundToFifteen(){
        rounds.value = 15
    }
}

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
        navigateToScreen1
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
    navigateToScreen1: () -> Unit
){
    @Composable
    fun CreateRadioButtonRound(num: Int, function: ()->Unit){
        Row{
            RadioButton(
                selected = (round == num),
                onClick = function
            )
            Text(num.toString())
        }
    }
    @Composable
    fun CreateRadioButtonDifficulty(num: Int, function: ()->Unit, text: String){
        Row{
            RadioButton(
                selected = (difficulty == num),
                onClick = function
            )
            Text(text)
        }
    }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Row{
            Text("Difficulty")
            Column {
                CreateRadioButtonDifficulty(1, easyDifficulty, "Easy")
                CreateRadioButtonDifficulty(2, normalDifficulty, "Normal")
                CreateRadioButtonDifficulty(3, hardDifficulty, "Hard")
            }
        }
        Spacer(Modifier.height(30.dp))
        Row{
            Text("Rounds")
            Column {
                CreateRadioButtonRound(5, roundToFive)
                CreateRadioButtonRound(10, roundToTen)
                CreateRadioButtonRound(15, roundToFifteen)
            }
        }
        Spacer(Modifier.height(40.dp))
        Button(onClick = navigateToScreen1){
            Text("Return to menu")
        }
    }
}