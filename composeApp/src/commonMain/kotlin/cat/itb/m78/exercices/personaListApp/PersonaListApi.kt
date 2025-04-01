package cat.itb.m78.exercices.personaListApp

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

@Serializable
data class Persona(
    val id: Int,
    val name: String,
    val arcana: String,
    val description: String?,
    val image: String?,
    @SerialName("query") val apiName: String
)

object PersonaListApi{
    private const val URL = "https://persona-compendium.onrender.com/personas/"
    private val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30.seconds.inWholeMilliseconds
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
    }
    suspend fun getList() = client.get(URL).body<List<Persona>?>()
    suspend fun getPersona(name : String) = client.get("$URL$name").body<Persona?>()
}