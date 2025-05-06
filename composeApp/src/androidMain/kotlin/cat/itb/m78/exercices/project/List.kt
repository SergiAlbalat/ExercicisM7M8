package cat.itb.m78.exercices.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun List(){
    val viewModel = viewModel{ListViewModel()}
    ListScreen(
        viewModel.markers.value
    )
}

@Composable
fun ListScreen(
    markers: List<Marker>
){
    Column(Modifier.fillMaxSize()) {
        for(i in markers){
            TextButton(onClick = {}) {
                Text(i.title)
            }
        }
    }
}