package cat.itb.m78.exercices.trivia

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TriviaGameScreenViewModel : ViewModel(){
    val settings = TrivialSettingsManager.get()
    val score = mutableStateOf(0)
    val questionCounter = mutableStateOf(1)
    val currentQuestion = mutableStateOf(Question(questions[0], answers[0], keys[0]))
    fun scoreUp(navigateToScoreScreen: (Int) -> Unit){
        score.value += 100
        if (questionCounter.value < settings.rounds){
            questionCounter.value++
            currentQuestion.value = nextQuestion(questionCounter.value-1)
        }
        if(questionCounter.value == settings.rounds){
            navigateToScoreScreen(score.value)
        }
    }

    fun nextQuestion(questionNum: Int) : Question{
        return Question(questions[questionNum], answers[questionNum], keys[questionNum])
    }

    fun answer(answerNumber: Int, navigateToScoreScreen: (Int)->Unit){
        if(settings.difficulty == 2){
            currentQuestion.value = currentQuestion.value.withAnswers(3)
        }else if(settings.difficulty == 1){
            currentQuestion.value = currentQuestion.value.withAnswers(2)
        }
        if(answerNumber == currentQuestion.value.valid){
            scoreUp(navigateToScoreScreen)
        }else{
            navigateToScoreScreen(score.value)
        }
    }
    // 1 . comprovar si es correcte
    // 2. preparar nova pregunta
}