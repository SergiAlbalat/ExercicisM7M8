package cat.itb.m78.exercices.personaListApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.io.IOException

class GeneralScreenViewModel : ViewModel(){
    val personas = mutableStateOf<List<Persona>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default){
//            try{
            personas.value = PersonaListApi.getList()
//            }catch (e : IOException) {
//                println("esrersersersesr")
//
//            }

        }


    }
}