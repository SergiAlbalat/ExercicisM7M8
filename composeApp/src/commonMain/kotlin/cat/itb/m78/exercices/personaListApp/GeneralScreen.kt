package cat.itb.m78.exercices.personaListApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PersonaListGeneralScreen(navigateToDetailedScreen: (String)->Unit){
    val viewModel = viewModel{ GeneralScreenViewModel() }
    PersonaListGenerealScreenView(
        viewModel.personas.value,
        navigateToDetailedScreen
    )
}

@Composable
fun PersonaListGenerealScreenView(
    personas: List<Persona>?,
    navigateToDetailedScreen: (String) -> Unit
){
    if(personas != null) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(personas) {persona ->
                TextButton({navigateToDetailedScreen(persona.apiName)}){
                    Text(persona.name, textAlign = TextAlign.Center)
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }else{
        CircularProgressIndicator()
    }
}