package cat.itb.m78.exercices.personaListApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun PersonaListDetailedScreen(name: String){
    val viewModel = viewModel{ PersonaListDetailedScreenViewModel(name) }
    PersonaListDetailedScreenView(
        viewModel.persona.value,
        viewModel::addFavourite
    )
}

@Composable
fun PersonaListDetailedScreenView(
    persona: PersonaWithFavorites?,
    addFavourite: (PersonaWithFavorites) -> Unit
){
    Scaffold(
        topBar = {
            if (persona != null) {
                TextButton(onClick = {addFavourite(persona)}){
                    if(persona.isFab){
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = null
                        )
                    }else{
                        Icon(
                            Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (persona != null) {
                AsyncImage(
                    model = persona.persona.image,
                    contentDescription = "image of the persona",
                    Modifier.size(300.dp)
                )
                Text("ID: " + persona.persona.id)
                Text("Name:  " + persona.persona.name)
                Text("Arcana: " + persona.persona.arcana)
                Text("Description: " + persona.persona.description, textAlign = TextAlign.Center)
            } else {
                CircularProgressIndicator()
            }
        }
    }
}