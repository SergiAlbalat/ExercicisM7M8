package cat.itb.m78.exercices.Avançats

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class oteloViewModel : ViewModel(){
    var board = List(10){List<Boolean?>(10){null} }
    var whitePlayer = true
    fun boxChange(x: Int, y: Int, whitePlayer: Boolean) : List<List<Boolean?>>{
        val newBoard: List<List<Boolean?>>
        if(whitePlayer){
            newBoard = board.updateValue(x,y,true)
        }else{
            newBoard = board.updateValue(x,y,false)
        }
        return newBoard
    }
    fun playerMove(x: Int, y: Int){
        board = boxChange(x, y, whitePlayer)
        whitePlayer = !whitePlayer
    }
}

fun <X> List<List<X?>>.updateValue(i: Int, j: Int, value: X) : List<List<X?>>{
    val mutableBoard = map {it.toMutableList()}
    mutableBoard[i][j] = value
    return mutableBoard
}

@Composable
fun OteloApp(){
    val viewModel = viewModel{ oteloViewModel() }
    OteloView()
}

@Composable
fun OteloView(){

}