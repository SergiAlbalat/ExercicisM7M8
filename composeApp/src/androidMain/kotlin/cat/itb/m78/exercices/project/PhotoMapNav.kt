package cat.itb.m78.exercices.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
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
    @Serializable
    data class CreateMarker(val lat: Float, val long: Float)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoMapNav(){
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            Column {
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
        },
        drawerState = drawerState,
        gesturesEnabled =  !drawerState.isClosed
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text("CamMap")},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if(drawerState.isClosed){
                                    drawerState.open()
                                }else{
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            }
        ) { contentPadding->
            Column(Modifier.padding(contentPadding)){
                NavHost(navController = navController, startDestination = PhotoMapScreens.CameraFeature) {
                    composable<PhotoMapScreens.CameraFeature> {
                        FeatureThatRequiresCameraPermission{navController.navigate(PhotoMapScreens.Map)}
                    }
                    composable<PhotoMapScreens.Map> {
                        Map({
                                lat, lon ->
                            val destination = PhotoMapScreens.CreateMarker(lat.toFloat(), lon.toFloat())
                            navController.navigate(destination)
                        }
                        )
                    }
                    composable<PhotoMapScreens.List> {
                        List()
                    }
                    composable<PhotoMapScreens.CreateMarker> {
                        it.savedStateHandle
                        CreateMarker(
                            it.toRoute<PhotoMapScreens.CreateMarker>().lat.toDouble(),
                            it.toRoute<PhotoMapScreens.CreateMarker>().long.toDouble(),
                            { navController.navigate(PhotoMapScreens.Map) },
                            {navController.navigate(PhotoMapScreens.Camera)},
                            it.savedStateHandle
                        )
                    }
                    composable<PhotoMapScreens.Camera>{
                        CameraScreen(
                            onPhotoCaptured = { savedUri ->
                                savedUri?.let { uri ->
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("PHOTO_URI_KEY", uri.toString())

                                    navController.popBackStack()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}