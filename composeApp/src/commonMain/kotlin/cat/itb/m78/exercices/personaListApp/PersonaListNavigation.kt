package cat.itb.m78.exercices.personaListApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object PersonaListScreens{
    @Serializable
    data object GeneralScreen
    @Serializable
    data object FavouritesScreen
    @Serializable
    data class DetailedScreen(val name: String)
}

@Composable
fun PersonaListNavegation(){
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomAppBar(
            actions = {
                TextButton(onClick = { navController.navigate(PersonaListScreens.GeneralScreen) }, Modifier.fillMaxWidth(0.5f)){
                    Column(horizontalAlignment =  Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = null
                        )
                        Text("Full List")
                    }
                }
                TextButton(onClick = { navController.navigate(PersonaListScreens.FavouritesScreen) }, Modifier.fillMaxWidth()){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Outlined.Favorite,
                            contentDescription = null
                        )
                        Text("Favourites")
                    }
                }
            })
        }
    ){
        NavHost(
            navController = navController,
            startDestination = PersonaListScreens.GeneralScreen
        ) {
            composable<PersonaListScreens.GeneralScreen> {
                PersonaListGeneralScreen(
                    { navController.navigate(PersonaListScreens.DetailedScreen(it)) }
                )
            }
            composable<PersonaListScreens.DetailedScreen> {
                PersonaListDetailedScreen(
                    it.toRoute<PersonaListScreens.DetailedScreen>().name
                )
            }
            composable<PersonaListScreens.FavouritesScreen> {
                FavouritesScreen(
                    { navController.navigate(PersonaListScreens.DetailedScreen(it)) }
                )
            }
        }
    }
}