package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class CounterViewModel : ViewModel(){
    var counter1 = mutableStateOf(0)
    var counter2 = mutableStateOf(0)
    val buttonText = "Score"
    val resetText = "Reset"
    fun counter1ScoreUp(){
        counter1.value++
    }
    fun counter2ScoreUp(){
        counter2.value++
    }
    fun resetCounters(){
        counter1.value = 0
        counter2.value = 0
    }
}

@Composable
fun CounterApp(){
    val viewModel = viewModel{ CounterViewModel() }
    CounterAppView(viewModel.counter1.value, viewModel.counter2.value, viewModel.buttonText, viewModel.resetText, viewModel::counter1ScoreUp, viewModel::counter2ScoreUp, viewModel::resetCounters)
}

@Composable
fun CounterAppView(counter1: Int, counter2: Int, buttonText: String, resetText: String, counter1ScoreUp: ()-> Unit, counter2ScoreUp: ()-> Unit, resetCounters: ()-> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()){
        Row(){
            Text("$counter1", modifier = Modifier.padding(40.dp, 10.dp))
            Text("$counter2", modifier = Modifier.padding(40.dp, 10.dp))
        }
        Row(){
            Button(onClick = counter1ScoreUp, modifier = Modifier.padding(3.dp)){
                Text(buttonText)
            }
            Button(onClick = counter2ScoreUp, modifier = Modifier.padding(3.dp)){
                Text(buttonText)
            }
        }
        Button(onClick = resetCounters){
            Text(resetText)
        }
    }
}