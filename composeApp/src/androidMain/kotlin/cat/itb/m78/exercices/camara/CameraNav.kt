package cat.itb.m78.exercices.camara

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object CameraScreens {
    @Serializable
    data object CameraFeature
    @Serializable
    data object Camera
    @Serializable
    data class Gallery(val photos : List<String>)
}

@Composable
fun CameraNav(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CameraScreens.CameraFeature){
        composable<CameraScreens.CameraFeature>{
            FeatureThatRequiresCameraPermission({ navController.navigate(CameraScreens.Camera) })
        }
        composable<CameraScreens.Camera> {
            CameraScreen({navController.navigate(CameraScreens.Gallery(it))})
        }
        composable<CameraScreens.Gallery> {
            Gallery(it.toRoute<CameraScreens.Gallery>().photos, {navController.navigate(CameraScreens.Camera)})
        }
    }
}