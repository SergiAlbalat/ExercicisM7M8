package cat.itb.m78.exercices.trivia

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TriviaGameScreenViewModel : ViewModel(){
    val settings = TrivialSettingsManager.get()
    val score = mutableStateOf(0)
    val questionCounter = mutableStateOf(1)
    private val apiQuestions = mutableStateOf<Questions?>(null)
    val currentQuestion = mutableStateOf<QuestionApi?>(null)

    init {
        viewModelScope.launch(Dispatchers.Default){
            apiQuestions.value = TriviaApi.getQuestions()
            currentQuestion.value = generateFirstQuestion()
        }
    }

    private fun generateFirstQuestion(): QuestionApi{
        var firstQuestion = apiQuestions.value!!.results[0]
        firstQuestion.shuffleAnswers()
        if(settings.difficulty == 2){
            firstQuestion = firstQuestion.withAnswers(3)
        }else if(settings.difficulty == 1){
           firstQuestion = firstQuestion.withAnswers(2)
        }
        return firstQuestion
    }

    private fun scoreUp(navigateToScoreScreen: (Int) -> Unit){
        score.value += 100
        if (questionCounter.value < settings.rounds){
            questionCounter.value++
            currentQuestion.value = apiQuestions.value!!.results[questionCounter.value-1]
            generateAnswers()
        }else if(questionCounter.value == settings.rounds){
            apiQuestions.value = null
            currentQuestion.value = null
            navigateToScoreScreen(score.value)
        }
    }

    private fun generateAnswers(){
        currentQuestion.value?.shuffleAnswers()
        if(settings.difficulty == 2){
            currentQuestion.value = currentQuestion.value?.withAnswers(3)
        }else if(settings.difficulty == 1){
            currentQuestion.value = currentQuestion.value?.withAnswers(2)
        }
    }

    fun answer(answerNumber: Int, navigateToScoreScreen: (Int)->Unit){
        if(answerNumber == currentQuestion.value?.valid){
            scoreUp(navigateToScoreScreen)
        }else{
            apiQuestions.value = null
            currentQuestion.value = null
            navigateToScoreScreen(score.value)
        }
    }
}