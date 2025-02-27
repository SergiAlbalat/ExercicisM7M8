package cat.itb.m78.exercices.api

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
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
data class Embassaments(
    @SerialName("estaci") val estaci: String,
    @SerialName("nivell_absolut") val nivell: String,
    @SerialName("dia") val dia: String,
    @SerialName("percentatge_volum_embassat") val percentatge: String,
    @SerialName("volum_embassat") val volum: String
)

object EstatEmbassamentsApi{
    private val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list(url: String) = client.get(url).body<List<Embassaments>?>()
}

object EstatEmbassamentsScreens{
    @Serializable
    data object GeneralScreen
    @Serializable
    data object DetailedScreen
}

@Composable
fun EstatEmbassamentsNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = EstatEmbassamentsScreens.GeneralScreen){
        composable<EstatEmbassamentsScreens.GeneralScreen>{
            EstatEmbassamentsGeneralApp({navController.navigate(EstatEmbassamentsScreens.DetailedScreen)})
        }
        composable<EstatEmbassamentsScreens.DetailedScreen>{
            EstatEmbassamentsDetailedApp()
        }
    }
}

class EstatEmbassamentsGeneralViewModel : ViewModel(){
    private val settings = Settings()
    val embassaments = mutableStateOf<List<Embassaments>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            embassaments.value = EstatEmbassamentsApi.list("https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json")
        }
    }
    fun saveAndNavigate(estaci: String, navigateToDetailedScreen: () -> Unit){
        settings["estaci"] =  estaci
        navigateToDetailedScreen()
    }
}

@Composable
fun EstatEmbassamentsGeneralApp(navigateToDetailedScreen: ()->Unit){
    val viewModel = viewModel{EstatEmbassamentsGeneralViewModel()}
    EstatEmbassamentGeneralView(
        viewModel.embassaments.value,
        navigateToDetailedScreen,
        viewModel::saveAndNavigate
    )
}

@Composable
fun EstatEmbassamentGeneralView(
    embassaments: List<Embassaments>?,
    navigateToDetailedScreen: () -> Unit,
    saveAndNavigate: (String, ()->Unit)->Unit
){
    if(embassaments != null){
        LazyColumn(Modifier.fillMaxSize()) {
            items(embassaments){ embassament ->
                Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
                    TextButton(onClick = {saveAndNavigate(embassament.estaci, navigateToDetailedScreen)}){
                        Text(embassament.estaci, textAlign = TextAlign.Center, fontSize = 1.em)
                    }
                    Text("Nivell: ${embassament.nivell}", textAlign = TextAlign.Center)
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

class EstatEmbassamentsDetailedViewModel: ViewModel(){
    private val settings = Settings()
    val embassaments = mutableStateOf<List<Embassaments>?>(null)
    private val estacio: String? = settings["estaci"]
    init {
        viewModelScope.launch(Dispatchers.Default) {
            embassaments.value = EstatEmbassamentsApi.list("https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json?estaci=${estacio}")
        }
    }
}

@Composable
fun EstatEmbassamentsDetailedApp(){
    val viewModel = viewModel{EstatEmbassamentsDetailedViewModel()}
    EstatEmbassamentsDetailedView(
        viewModel.embassaments.value
    )
}

@Composable
fun EstatEmbassamentsDetailedView(
    embassaments: List<Embassaments>?
){
    if(embassaments != null){
        LazyColumn(Modifier.fillMaxSize()) {
            items(embassaments){ embassament ->
                Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
                    Text(embassament.estaci, textAlign = TextAlign.Center, fontSize = 1.em)
                    Text("Dia: ${embassament.dia}")
                    Text("Nivell: ${embassament.nivell}", textAlign = TextAlign.Center)
                    Text("Volum embassat: ${embassament.volum}")
                    Text("Percentatge de volum embassat: ${embassament.percentatge}")
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}