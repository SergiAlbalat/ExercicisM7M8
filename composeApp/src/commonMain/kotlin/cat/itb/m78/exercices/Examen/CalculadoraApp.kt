package cat.itb.m78.exercices.Examen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CalculadoraApp(navigateToFinalScreen: (Int)->Unit){
    val viewModel = viewModel{ CalculadoraViewModel() }
    CalculadoraMainView(
        viewModel.result.value,
        viewModel.userInput.value,
        viewModel::userInputChange,
        viewModel::operationToSum,
        viewModel::operationToRest,
        viewModel::operationToMult,
        viewModel::operationToDiv,
        viewModel::calculateResult,
        navigateToFinalScreen,
        viewModel.operation.value
    )
}

@Composable
fun CalculadoraMainView(
    result: Int,
    userInput: String,
    userInputChange: (String)->Unit,
    operationToSum: ()->Unit,
    operationToRest: ()->Unit,
    operationToMult: ()->Unit,
    operationToDiv: ()->Unit,
    calculateResult: ()->Unit,
    navigateToFinalScreen: (Int) -> Unit,
    operation: Int
){
    @Composable
    fun PrintTextButton(value: String){
        if(value == "+" && operation == 1){
            Text(value, fontWeight = FontWeight.Bold)
        }else if(value == "-" && operation == 2){
            Text(value, fontWeight = FontWeight.Bold)
        }else if(value == "*" && operation == 3){
            Text(value, fontWeight = FontWeight.Bold)
        }else if(value == "/" && operation == 4){
            Text(value, fontWeight = FontWeight.Bold)
        }else{
            Text(value)
        }
    }
    Column(Modifier.fillMaxSize().background(Color.Yellow), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(result.toString(), fontSize = 2.em)
        Card(Modifier.padding(10.dp).width(300.dp)){
            Row(Modifier.padding(10.dp)){
                Button(onClick = operationToSum, Modifier.padding(5.dp)){
                    PrintTextButton("+")
                }
                Button(onClick = operationToRest, Modifier.padding(5.dp)){
                    PrintTextButton("-")
                }
                Button(onClick = operationToMult, Modifier.padding(5.dp)){
                    PrintTextButton("*")
                }
                Button(onClick = operationToDiv, Modifier.padding(5.dp)){
                    PrintTextButton("/")
                }
            }
            TextField(
                userInput,
                onValueChange = userInputChange,
                Modifier.fillMaxWidth().padding(5.dp).border(BorderStroke(width = 2.dp, color = Color.DarkGray), shape = RoundedCornerShape(5.dp))
            )
            Row(Modifier.padding(10.dp).fillMaxWidth(), Arrangement.Center){
                OutlinedButton(onClick = {navigateToFinalScreen(result)}, Modifier.padding(5.dp)){
                    Text("End")
                }
                Button(onClick = calculateResult, Modifier.padding(5.dp)){
                    Text("Calculate")
                }
            }
        }
    }
}