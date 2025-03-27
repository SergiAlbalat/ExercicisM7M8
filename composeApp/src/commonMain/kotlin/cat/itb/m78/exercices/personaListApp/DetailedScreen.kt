package cat.itb.m78.exercices.personaListApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun PersonaListDetailedScreen(name: String, navigateToGeneralScreen: ()->Unit){
    val viewModel = viewModel{ PersonaListDetailedScreenViewModel(name) }
    PersonaListDetailedScreenView(
        viewModel.persona.value,
        navigateToGeneralScreen
    )
}

@Composable
fun PersonaListDetailedScreenView(
    persona: Persona?,
    navigateToGeneralScreen: () -> Unit
){
    Column {
        Button(onClick = navigateToGeneralScreen) {
            Text("Go Back")
        }
        if (persona != null) {
            AsyncImage(
                model = persona.image,
                contentDescription = "image of the persona",
                Modifier.size(300.dp)
            )
            Text("ID: " + persona.id)
            Text("Name:  " + persona.name)
            Text("Arcana: " + persona.arcana)
            Text("Description: " + persona.description)
        } else {
            CircularProgressIndicator()
        }
    }
}