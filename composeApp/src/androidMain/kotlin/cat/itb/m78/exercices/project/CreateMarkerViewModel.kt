package cat.itb.m78.exercices.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.bd.database
import com.google.android.gms.maps.model.LatLng
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toSet


class CreateMarkerViewModel(lat: Double, lon: Double, private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val settings : Settings = Settings()
    val coords = LatLng(lat, lon)
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val photoUri = savedStateHandle.getStateFlow<String?>("PHOTO_URI_KEY", null)
    fun lastPhoto(){
        savedStateHandle.set("PHOTO_URI_KEY", settings.getStringOrNull("photoCam"))
    }
    fun titleChange(it : String){
        title.value = it
    }
    fun descriptionChange(it: String){
        description.value = it
    }
    fun addMarker(navigateToMapParam:()->Unit){
        database.markersQueries.insert(coords.latitude, coords.longitude, title.value, description.value)
        if(photoUri.value != null){
            settings["photoCam"] = photoUri.value
        }
        navigateToMapParam()
    }
}