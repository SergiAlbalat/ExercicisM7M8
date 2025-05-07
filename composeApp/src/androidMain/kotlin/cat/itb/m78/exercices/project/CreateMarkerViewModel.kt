package cat.itb.m78.exercices.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.bd.database
import com.google.android.gms.maps.model.LatLng

class CreateMarkerViewModel(lat: Double, lon: Double, private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val coords = LatLng(lat, lon)
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val photoUri = savedStateHandle.getStateFlow<String?>("PHOTO_URI_KEY", null)
    fun titleChange(it : String){
        title.value = it
    }
    fun descriptionChange(it: String){
        description.value = it
    }
    fun addMarker(navigateToMapParam:()->Unit){
        database.markersQueries.insert(coords.latitude, coords.longitude, title.value, description.value)
        navigateToMapParam()
    }
}