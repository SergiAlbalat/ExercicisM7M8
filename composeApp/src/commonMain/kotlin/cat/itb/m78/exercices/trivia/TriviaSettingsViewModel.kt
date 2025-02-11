package cat.itb.m78.exercices.trivia

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TriviaSettingsViewModel : ViewModel(){
    private var settings = TrivialSettingsManager.get()
    val difficulty = mutableStateOf(settings.difficulty)
    val rounds = mutableStateOf(settings.rounds)
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
    fun saveExit(navigateToMenuScreen: ()->Unit){
        settings = TrivialSettings(rounds.value, difficulty.value)
        TrivialSettingsManager.update(settings)
        navigateToMenuScreen()
    }
}