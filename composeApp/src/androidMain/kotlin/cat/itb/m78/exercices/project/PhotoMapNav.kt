package cat.itb.m78.exercices.project

import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

object PhotoMapScreens{
    @Serializable
    data object CameraFeature
    @Serializable
    data object Map
    @Serializable
    data object List
    @Serializable
    data object Camera
}

@Composable
fun PhotoMapNav(){
    val navController = rememberNavController()
    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawerItem(
                label = { Text("Map") },
                selected = false,
                onClick = {navController.navigate(PhotoMapScreens.Map)}
            )
            NavigationDrawerItem(
                label = { Text("Location List") },
                selected = false,
                onClick = {navController.navigate(PhotoMapScreens.List)}
            )
        }
    ) {
        NavHost(navController = navController, startDestination = PhotoMapScreens.CameraFeature) {
            composable<PhotoMapScreens.CameraFeature> {
                FeatureThatRequiresCameraPermission{navController.navigate(PhotoMapScreens.Map)}
            }
            composable<PhotoMapScreens.Map> {
                Map()
            }
            composable<PhotoMapScreens.List> {
                List()
            }
        }
    }
}