package cat.itb.m78.exercices.trivia

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TriviaGameScreenViewModel : ViewModel(){
    val settings = TrivialSettingsManager.get()
    val score = mutableStateOf(0)
    val questionCounter = mutableStateOf(1)
    val currentQuestion = mutableStateOf(Question(questions[0], answers[0], keys[0]))
    fun scoreUp(){
        score.value += 100
        questionCounter.value++
    }

    fun printQuestion(): String{
        if(questionCounter.value != settings.rounds+1){
            return currentQuestion.value.question
        }else{
            return ""
        }
    }

    fun answer(answer: Int){
        // 1 . comprovar si es correcte
        // 2. preparar nova pregunta
    }
}