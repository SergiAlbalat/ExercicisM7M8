package cat.itb.m78.exercices.personaListApp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    NavHost(navController = navController, startDestination = PersonaListScreens.GeneralScreen){
        composable<PersonaListScreens.GeneralScreen>{
            PersonaListGeneralScreen(
                {navController.navigate(PersonaListScreens.DetailedScreen(it))},
                {navController.navigate(PersonaListScreens.FavouritesScreen)}
            )
        }
        composable<PersonaListScreens.DetailedScreen>{
            PersonaListDetailedScreen(
                it.toRoute<PersonaListScreens.DetailedScreen>().name,
                {navController.navigate(PersonaListScreens.GeneralScreen)}
            )
        }
        composable<PersonaListScreens.FavouritesScreen> {
            FavouritesScreen(
                {navController.navigate(PersonaListScreens.GeneralScreen)},
                {navController.navigate(PersonaListScreens.DetailedScreen(it))}
            )
        }
    }
}