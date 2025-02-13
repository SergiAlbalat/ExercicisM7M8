package cat.itb.m78.exercices.trivia

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TriviaGameScreenViewModel : ViewModel(){
    val settings = TrivialSettingsManager.get()
    private val score = mutableStateOf(0)
    val questionCounter = mutableStateOf(1)
    val currentQuestion = mutableStateOf(generateFirstQuestion())
    private fun generateFirstQuestion(): Question{
        val firstQuestion = questions[0]
        firstQuestion.shuffleAnswers()
        return firstQuestion
    }

    private fun scoreUp(navigateToScoreScreen: (Int) -> Unit){
        score.value += 100
        if (questionCounter.value < settings.rounds){
            questionCounter.value++
            currentQuestion.value = questions[questionCounter.value-1]
            generateAnswers()
        }else if(questionCounter.value == settings.rounds){
            navigateToScoreScreen(score.value)
        }
    }

    private fun generateAnswers(){
        currentQuestion.value.shuffleAnswers()
        if(settings.difficulty == 2){
            currentQuestion.value = currentQuestion.value.withAnswers(3)
        }else if(settings.difficulty == 1){
            currentQuestion.value = currentQuestion.value.withAnswers(2)
        }
    }

    fun answer(answerNumber: Int, navigateToScoreScreen: (Int)->Unit){
        if(answerNumber == currentQuestion.value.valid){
            scoreUp(navigateToScoreScreen)
        }else{
            navigateToScoreScreen(score.value)
        }
    }
}