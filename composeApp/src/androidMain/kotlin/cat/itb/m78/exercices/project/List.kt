package cat.itb.m78.exercices.project

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun List(){
    val viewModel = viewModel{ListViewModel()}
    ListScreen()
}

@Composable
fun ListScreen(){

}