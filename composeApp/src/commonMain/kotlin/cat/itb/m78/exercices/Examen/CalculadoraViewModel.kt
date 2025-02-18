package cat.itb.m78.exercices.Examen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculadoraViewModel : ViewModel(){
    val operation = mutableStateOf(1)
    val result = mutableStateOf(0)
    val userInput = mutableStateOf("")
    fun userInputChange(it: String){
        userInput.value = it
    }
    fun operationToSum(){
        operation.value = 1
    }
    fun operationToRest(){
        operation.value = 2
    }
    fun operationToMult(){
        operation.value = 3
    }
    fun operationToDiv(){
        operation.value = 4
    }
    fun calculateResult(){
        if(userInput.value.matches(Regex("^\\d+\$"))){
            when(operation.value){
                1-> {
                    result.value += userInput.value.toInt()
                }
                2-> {
                    result.value -= userInput.value.toInt()
                }
                3-> {
                    result.value *= userInput.value.toInt()
                }
                4-> {
                    result.value /= userInput.value.toInt()
                }
            }
        }
        userInput.value = ""
    }
}