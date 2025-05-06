package cat.itb.m78.exercices.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CreateMarker(lat: Double, lon: Double, navigateToMap:()->Unit){
    val viewModel = viewModel{CreateMarkerViewModel(lat, lon)}
    CreateMarkerScreen(
        viewModel.title.value,
        viewModel.description.value,
        viewModel::titleChange,
        viewModel::descriptionChange,
        viewModel::addMarker,
        navigateToMap
    )
}

@Composable
fun CreateMarkerScreen(
    title: String,
    description: String,
    titleChange: (String)->Unit,
    descriptionChange: (String)->Unit,
    addMarker: (()->Unit)->Unit,
    navigateToMap: () -> Unit
){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        TextField(
            title,
            label = {Text("Title")},
            onValueChange = titleChange
        )
        TextField(
            description,
            label = {Text("Description")},
            onValueChange = descriptionChange
        )
        Button(onClick = { (addMarker(navigateToMap)) }) {
            Text("Add Marker")
        }
    }
}