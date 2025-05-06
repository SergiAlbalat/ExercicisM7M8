package cat.itb.m78.exercices.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class Marker(val id: Long, val coords: LatLng, val title: String)

class MapViewModel : ViewModel() {
    val initialZone = LatLng(41.38879,2.15899)
    val markers = mutableStateOf(updateMarkers())
    private fun updateMarkers(): List<Marker>{
        val markersUpdated = mutableListOf<Marker>()
        viewModelScope.launch(Dispatchers.Default) {
            for(i in database.markersQueries.selectAll().executeAsList()){
                markersUpdated += Marker(i.id, LatLng(i.lat, i.long), i.title)
            }
        }
        return markersUpdated
    }
}