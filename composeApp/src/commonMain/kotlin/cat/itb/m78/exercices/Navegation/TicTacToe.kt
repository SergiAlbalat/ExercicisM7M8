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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object TicTacToeScreens{
    @Serializable
    data object Screen1
    @Serializable
    data object Screen2
    @Serializable
    data class Screen3(val xWin: Boolean)
}

@Composable
fun TicTacToeNavegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TicTacToeScreens.Screen1){
        composable<TicTacToeScreens.Screen1>{
            TicTacToeScreen1View({navController.navigate(TicTacToeScreens.Screen2)})
        }
        composable<TicTacToeScreens.Screen2> {
            TicTacToeScreen2App({navController.navigate(TicTacToeScreens.Screen3(it))})
        }
        composable<TicTacToeScreens.Screen3> {
            TicTacToeScreen3View(it.toRoute<TicTacToeScreens.Screen3>().xWin, {navController.navigate(TicTacToeScreens.Screen2)})
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
    var values = mutableStateOf(List(3){List<Boolean?>(3){null} })
    fun changeValue(xCord: Int, yCord: Int){
        if(values.value[yCord][xCord] == null){
            if(xPlayer){
                val newValues = values.value.updateValue(yCord, xCord, true)
                values.value = newValues
            }else{
                val newValues = values.value.updateValue(yCord, xCord, false)
                values.value = newValues
            }
            xPlayer = !xPlayer
        }
    }
    fun checkWin(): Boolean{
        var win: Boolean = false
        for(i in 0..2){
            win = win||values.value[i][0]==values.value[i][1]&&values.value[i][1]==values.value[i][2]&&values.value[i][0]!=null
            win = win||values.value[0][i]==values.value[1][i]&&values.value[1][i]==values.value[2][i]&&values.value[0][i]!=null
        }
        win = win||values.value[0][0]==values.value[1][1]&&values.value[1][1]==values.value[2][2]&& values.value[0][0]!=null
        win = win||values.value[0][2]==values.value[1][1]&&values.value[1][1]==values.value[2][0]&&values.value[0][2]!=null
        return win
    }
    fun move(xCord: Int, yCord: Int, navigateToScreen3: (Boolean)->Unit){
        changeValue(xCord, yCord)
        if (checkWin()){
            navigateToScreen3(!xPlayer)
        }
    }
}

fun <T> List<List<T?>>.updateValue(i: Int, j: Int, value: T) : List<List<T?>>{
    val mutableBoard = map { it.toMutableList() }
    mutableBoard[i][j]=value
    return mutableBoard
}

@Composable
fun TicTacToeScreen2App(navigateToScreen3: (Boolean) -> Unit){
    val viewModel = viewModel{ TicTacToeViewModel() }
    TicTacToeSceen2View(
        viewModel.values.value,
        viewModel::move,
        navigateToScreen3
    )
}

@Composable
fun TicTacToeSceen2View(
    values: List<List<Boolean?>>,
    move: (Int, Int, (Boolean)->Unit)->Unit,
    navigateToScreen3: (Boolean) -> Unit
){
    fun getValue(xCord: Int, yCord: Int) : Boolean?{
        return values[yCord][xCord]
    }
    fun writeValue(xCord : Int, yCord: Int) : String{
        when(getValue(xCord, yCord)){
            null -> return ""
            true -> return "X"
            false -> return "O"
        }
    }
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        for(j in 0..2){
            Row {
                for (i in 0..2){
                    TextButton(onClick = { move(i,j,navigateToScreen3)  }, Modifier.size(100.dp).border(1.dp, Color.Black)){
                        Text(writeValue(i,j), color = Color.Blue, fontSize = 4.em)
                    }
                }
            }
        }
    }
}

@Composable
fun TicTacToeScreen3View(xWin: Boolean, navigateToScreen2: () -> Unit){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        if(xWin){
            Text("The winner is X")
        }else{
            Text("The winner is O")
        }
        Button(onClick = navigateToScreen2){
            Text("Play Again")
        }
    }
}