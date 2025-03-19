package cat.itb.m78.exercices.trivia

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Questions(
    @SerialName("results") val results: List<QuestionApi>
)

@Serializable
data class QuestionApi(
    @SerialName("question") val question: String,
    @SerialName("correct_answer") val correctAnswer: String,
    @SerialName("incorrect_answers") val incorrectAnswers: List<String>,
    var answers: List<String> = incorrectAnswers + correctAnswer,
    var valid: Int = 4
){
    fun shuffleAnswers(){
        answers = answers.shuffled()
        for(i in answers.indices){
            if(answers[i] == correctAnswer){
                valid = i+1
            }
        }
    }
    fun withAnswers(count: Int) : QuestionApi {
        if(valid > count){
            val newKey = (1..count).random()
            return QuestionApi(question, correctAnswer, incorrectAnswers, List(count){
                if(newKey-1==it){
                    answers[valid-1]
                }else{
                    answers[it]
                }
            }, newKey)
        }
        return QuestionApi(question, correctAnswer, incorrectAnswers, answers, valid)
    }
}

object TriviaApi{
    private val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getQuestions() = client.get("https://opentdb.com/api.php?amount=15&category=15&difficulty=easy&type=multiple").body<Questions?>()
}