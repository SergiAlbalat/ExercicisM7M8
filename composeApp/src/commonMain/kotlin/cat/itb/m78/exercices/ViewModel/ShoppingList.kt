package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

data class Item(val name: String, val quantity: Int)

class ShoppingListViewModel : ViewModel(){
    val shopList = mutableStateOf(listOf<Item>())
    val nameText = mutableStateOf("")
    val countText = mutableStateOf("")
    fun nameTextChange(it: String){
        nameText.value = it
    }
    fun countTextChange(it: String){
        countText.value = it
    }
    fun addToList(){
        if(nameText.value != "" && countText.value != ""){
            shopList.value += (Item(nameText.value, countText.value.toInt()))
        }
    }
}
@Composable
fun ShoppingListApp(){
    val viewModel = viewModel{ ShoppingListViewModel() }
    ShoppingListAppView(
        viewModel.nameText.value,
        viewModel::nameTextChange,
        viewModel.countText.value,
        viewModel::countTextChange,
        viewModel::addToList,
        viewModel.shopList.value
    )
}
@Composable
fun ShoppingListAppView(
    nameText: String,
    nameTextChange: (String)->Unit,
    countText: String,
    countTextChange: (String)->Unit,
    addToList: ()->Unit,
    shopList: List<Item>
){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        Card(modifier = Modifier.padding(10.dp)){
            Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(nameText, onValueChange = nameTextChange, label = {Text("Name")})
                TextField(countText, onValueChange = countTextChange, label = {Text("Amount")})
                Button(onClick = addToList){
                    Text("Add")
                }
            }
        }
        LazyColumn (Modifier.padding(10.dp)){
            items(shopList.size){ item ->
                Card(modifier = Modifier.padding(10.dp).width(400.dp)){
                    Row(modifier = Modifier.padding(10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                        Text(shopList[item].name)
                        Spacer(Modifier.width(200.dp))
                        Text("${shopList[item].quantity}")
                    }
                }
            }
        }
    }
}