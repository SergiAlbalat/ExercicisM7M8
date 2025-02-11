package cat.itb.m78.exercices.trivia

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object TriviaScreens{
    @Serializable
    data object MenuScreen
    @Serializable
    data object GameScreen
    @Serializable
    data class ScoreScreen(val score: Int)
    @Serializable
    data object ScreenSettings
}

@Composable
fun TriviaNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TriviaScreens.MenuScreen){
        composable<TriviaScreens.MenuScreen>{
            TriviaMenuScreenView({navController.navigate(TriviaScreens.GameScreen)}, {navController.navigate(TriviaScreens.ScreenSettings)})
        }
        composable<TriviaScreens.GameScreen>{
            TriviaGameScreenApp({navController.navigate(TriviaScreens.ScoreScreen(it))})
        }
        composable<TriviaScreens.ScoreScreen> {
            TriviaScoreScreenView(it.toRoute<TriviaScreens.ScoreScreen>().score, {navController.navigate(TriviaScreens.MenuScreen)})
        }
        composable<TriviaScreens.ScreenSettings> {
            TriviaSettingsApp({navController.navigate(TriviaScreens.MenuScreen)})
        }
    }
}