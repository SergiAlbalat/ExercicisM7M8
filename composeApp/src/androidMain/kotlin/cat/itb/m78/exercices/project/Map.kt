package cat.itb.m78.exercices.project

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Map(navigateToCreateMarker: (Double, Double)->Unit){
    val viewModel = viewModel{MapViewModel()}
    MapScreen(
        viewModel.initialZone,
        navigateToCreateMarker,
        viewModel.markers.value
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapScreen(
    initialZone: LatLng,
    navigateToCreateMarker: (Double, Double) -> Unit,
    markers: List<Marker>
){
    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(initialZone, 15f)
    }
    Box{
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = {coords-> navigateToCreateMarker(coords.latitude, coords.longitude)}
        ){
            AdvancedMarker(
                state = MarkerState(position = LatLng(41.4534028,2.1861224)),
                title = "ITB"
            )
            for(i in markers){
                AdvancedMarker(
                    state = MarkerState(position = i.coords),
                    title = i.title
                )
            }
        }
    }
}