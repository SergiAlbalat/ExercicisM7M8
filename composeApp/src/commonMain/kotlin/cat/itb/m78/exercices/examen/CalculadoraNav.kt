package cat.itb.m78.exercices.examen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object CalculadoraScreens{
    @Serializable
    data object MainScreen
    @Serializable
    data class FinalScreen(val result: Int)
}

@Composable
fun CalculadoraNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CalculadoraScreens.MainScreen){
        composable<CalculadoraScreens.MainScreen> {
            CalculadoraApp { navController.navigate(CalculadoraScreens.FinalScreen(it)) }
        }
        composable<CalculadoraScreens.FinalScreen> {
            CalculadoraFinalScreen(it.toRoute<CalculadoraScreens.FinalScreen>().result)
        }
    }
}