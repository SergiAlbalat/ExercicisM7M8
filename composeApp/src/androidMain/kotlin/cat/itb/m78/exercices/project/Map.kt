package cat.itb.m78.exercices.project

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Map(){
    val viewModel = viewModel{MapViewModel()}
    MapScreen()
}

@Composable
fun MapScreen(){

}