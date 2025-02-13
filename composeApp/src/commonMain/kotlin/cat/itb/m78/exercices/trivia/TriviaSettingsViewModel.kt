package cat.itb.m78.exercices.trivia

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TriviaSettingsViewModel : ViewModel(){
    private var settings = TrivialSettingsManager.get()
    val difficulty = mutableStateOf(settings.difficulty)
    val rounds = mutableStateOf(settings.rounds)
    val time = mutableStateOf(settings.time)
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
    fun timeToFive(){
        time.value = 5
    }
    fun timeToTen(){
        time.value = 10
    }
    fun timeToTwenty(){
        time.value = 20
    }
    fun saveExit(navigateToMenuScreen: ()->Unit){
        settings = TrivialSettings(rounds.value, difficulty.value, time.value)
        TrivialSettingsManager.update(settings)
        navigateToMenuScreen()
    }
}