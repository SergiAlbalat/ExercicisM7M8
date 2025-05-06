package cat.itb.m78.exercices.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel(){
    val markers = mutableStateOf(updateMarkers())
    private fun updateMarkers(): List<Marker>{
        val markersUpdated = mutableListOf<Marker>()
        for(i in database.markersQueries.selectAll().executeAsList()){
                markersUpdated += Marker(i.id, LatLng(i.lat, i.long), i.title)
            }

        return markersUpdated
    }
}