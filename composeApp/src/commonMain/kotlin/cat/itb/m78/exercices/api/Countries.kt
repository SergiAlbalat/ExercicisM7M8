package cat.itb.m78.exercices.api

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Country(
    @SerialName("name") val name: String,
    @SerialName("capital") val capital: String,
    @SerialName("media") val media: CountryMedia
)

@Serializable
data class CountryMedia(
    @SerialName("flag") val flag: String
)

object CountriesApi{
    private const val URL = "https://api.sampleapis.com/countries/countries"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(URL).body<List<Country>?>()
}

class CountriesViewModel: ViewModel(){
    val countries = mutableStateOf<List<Country>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            countries.value = CountriesApi.list()
        }
    }
}

@Composable
fun CountriesApp(){
    val viewModel = viewModel{CountriesViewModel()}
    CountriesView(
        viewModel.countries.value
    )
}

@Composable
fun CountriesView(
    countries: List<Country>?
){
    if(countries != null){
        LazyColumn {
            items(countries){ country ->
                Row{
                    Text(country.name)
                    AsyncImage(
                        model = country.media.flag,
                        contentDescription = null,
                        Modifier.width(40.dp).height(30.dp)
                    )
                }
                Text(country.capital)
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}