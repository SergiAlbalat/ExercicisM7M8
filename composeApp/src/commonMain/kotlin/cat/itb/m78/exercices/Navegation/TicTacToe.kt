package cat.itb.m78.exercices.Navegation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

object TicTacToeScreens{
    @Serializable
    data object Screen1
    @Serializable
    data object Screen2
    @Serializable
    data class Screen3(val circleWin: Boolean)
}

@Composable
fun TicTacToeNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TicTacToeScreens.Screen1){
        composable<TicTacToeScreens.Screen1>{
            TicTacToeScreen1View({navController.navigate(TicTacToeScreens.Screen2)})
        }
        composable<TicTacToeScreens.Screen2> {
            TicTacToeScreen2App()
        }
    }
}

@Composable
fun TicTacToeScreen1View(navigateToScreen2: ()->Unit){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally){
        Button(onClick = navigateToScreen2){
            Text("Start")
        }
    }
}

class TicTacToeViewModel : ViewModel(){
    var xPlayer = true
    var values = mutableStateOf(List<Boolean?>(9){null})
    fun changeValue(numeroCasella: Int){
        if(values.value[numeroCasella] == null){
            if(xPlayer){

            }else{
                values.value[numeroCasella] = false
            }
            xPlayer = !xPlayer
        }
    }
}

fun <T> List<List<T?>>.updateValue(i: Int, j: Int, value: T) : List<List<T?>>{
    val mutableBoard = map { it.toMutableList() }
    mutableBoard[i][j]=value
    return mutableBoard
}

@Composable
fun TicTacToeScreen2App(){
    val viewModel = viewModel{ TicTacToeViewModel() }
    TicTacToeSceen2View(
        viewModel.values.value,
        viewModel::changeValue
    )
}

@Composable
fun TicTacToeSceen2View(
    values: List<Boolean?>,
    changeValue: (Int)->Unit
){
    fun getValue(numeroCasella: Int) : Boolean?{
        return values[numeroCasella]
    }
    fun writeValue(numeroCasella : Int) : String{
        when(getValue(numeroCasella)){
            null -> return ""
            true -> return "X"
            false -> return "O"
        }
    }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Row {
            for (i in 0..2){
                TextButton(onClick = { changeValue(i) }, Modifier.size(100.dp).border(1.dp, Color.Black)){
                    Text(writeValue(i))
                }
            }
        }
        Row {
            for (i in 0..2){
                TextButton(onClick = { changeValue(i+3) }, Modifier.size(100.dp).border(1.dp, Color.Black)){
                    Text(writeValue(i+3))
                }
            }
        }
        Row {
            for (i in 0..2){
                TextButton(onClick = { changeValue(i+6) }, Modifier.size(100.dp).border(1.dp, Color.Black)){
                    Text(writeValue(i+6))
                }
            }
        }
    }
}