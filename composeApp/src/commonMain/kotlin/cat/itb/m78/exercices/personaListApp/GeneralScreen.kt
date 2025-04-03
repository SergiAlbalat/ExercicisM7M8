package cat.itb.m78.exercices.personaListApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PersonaListGeneralScreen(
    navigateToDetailedScreen: (String)->Unit,
){
    val viewModel = viewModel{ GeneralScreenViewModel() }
    PersonaListGenerealScreenView(
        viewModel.personas.value,
        navigateToDetailedScreen,
        viewModel::addFavourite,
        viewModel.searchInput.value,
        viewModel::searchInputChange
    )
}

@Composable
fun PersonaListGenerealScreenView(
    personas: List<PersonaWithFavorites>?,
    navigateToDetailedScreen: (String) -> Unit,
    addFavourite: (PersonaWithFavorites) -> Unit,
    searchInput: String,
    searchInputChange: (String) -> Unit
){
    Scaffold(topBar = {
        TextField(
            searchInput,
            onValueChange = searchInputChange,
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
    }) { screenPadding ->
        Column(Modifier.padding(screenPadding).fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
            if(personas != null) {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(personas) {persona ->
                        Row(Modifier.fillMaxSize(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                            TextButton({navigateToDetailedScreen(persona.persona.apiName)}){
                                Text(persona.persona.name, textAlign = TextAlign.Center)
                            }
                            TextButton(onClick = {addFavourite(persona)}){
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
    }
}