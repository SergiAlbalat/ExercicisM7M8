package cat.itb.m78.exercices.api

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlin.random.Random

@Serializable
data class Joke(
    @SerialName("setup") val setup: String,
    @SerialName("punchline") val punchline: String
)

object JokesApi{
    private const val URL = "https://api.sampleapis.com/jokes/goodJokes"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(URL).body<List<Joke>?>()
}

class JokesViewModel : ViewModel(){
    val id = mutableStateOf(Random.nextInt(0,388))
    val currentJoke = mutableStateOf<List<Joke>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
            currentJoke.value = JokesApi.list()
        }
    }
}

@Composable
fun JokesApp(){
    val viewModel = viewModel{JokesViewModel()}
    JokesView(
        viewModel.currentJoke.value,
        viewModel.id.value
    )
}

@Composable
fun JokesView(
    currentJoke: List<Joke>?,
    id: Int
){
    if(currentJoke != null){
        Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
            Text(currentJoke[id].setup)
            Text(currentJoke[id].punchline)
        }
    }
}