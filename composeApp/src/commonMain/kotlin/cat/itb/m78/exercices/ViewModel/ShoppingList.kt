package cat.itb.m78.exercices.ViewModel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class ShoppingListViewModel : ViewModel(){

}
@Composable
fun ShoppingListApp(){
    val viewModel = viewModel{ ShoppingListViewModel() }
}
@Composable
fun ShoppingListAppView(){

}