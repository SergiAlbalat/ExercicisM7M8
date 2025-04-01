package cat.itb.m78.exercices.personaListApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavouritesScreen(
    navigateToGeneralScreen: ()->Unit,
    navigateToDetailedScreen: (String) -> Unit
){
    val viewModel = viewModel{ FavouritesScreenViewModel() }
    FavouritesScreenView(
        viewModel.personas.value,
        navigateToDetailedScreen,
        viewModel::removeFavourite,
        navigateToGeneralScreen
    )
}

@Composable
fun FavouritesScreenView(
    personas: List<PersonaWithFavorites>?,
    navigateToDetailedScreen: (String) -> Unit,
    removeFavourite: (PersonaWithFavorites) -> Unit,
    navigateToGeneralScreen: () -> Unit
){
    if(personas != null) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(personas) {persona ->
                Row(Modifier.fillMaxSize(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    TextButton({navigateToDetailedScreen(persona.persona.apiName)}){
                        Text(persona.persona.name, textAlign = TextAlign.Center)
                    }
                    TextButton(onClick = {removeFavourite(persona)}){
                        if (persona.isFab){
                            Icon(
                                Icons.Outlined.Favorite,
                                contentDescription = null
                            )
                        }else{
                            Icon(
                                Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                            )
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }else{
        CircularProgressIndicator()
    }
}