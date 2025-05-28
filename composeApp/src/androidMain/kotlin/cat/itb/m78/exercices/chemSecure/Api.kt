package cat.itb.m78.exercices.chemSecure

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName


@Serializable
data class Sensor (
    val volum: Float,
    val id : Int
)

@Serializable
data class Tank(
    @SerialName("Id") val id: String,
    @SerialName("Capacity") val capacity: Double,
    @SerialName("CurrentVolume") val currentVolume: Double,
    @SerialName("Type") val type: Int
)


class ChemSecureApi (id : String="") {
    // Atributs
    val url = "https://chemsecure-g3f3endkdxc6fsd4.northeurope-01.azurewebsites.net/api";
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun sendSensorData(sensor: Sensor): Boolean {
        try {
            client.post("$url/update-volume/${sensor.id}") {
                contentType(ContentType.Application.Json)
                setBody(sensor.volum) // Enviar objeto Sensor
            }
            return true // Éxito
        } catch (e: Exception) {
            println("Error al enviar datos: ${e.message}")
            return false // Fallo
        }
    }
    suspend fun getTanks(): List<Tank>{
        return client.get("$url/Tank/user").body<List<Tank>>()
    }
    fun close() {
        client.close() // Cierra el cliente cuando no se necesite
    }
}