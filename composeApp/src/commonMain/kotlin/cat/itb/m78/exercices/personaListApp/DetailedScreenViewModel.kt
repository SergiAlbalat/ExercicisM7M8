package cat.itb.m78.exercices.personaListApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonaListDetailedScreenViewModel(name: String) : ViewModel(){
    val persona = mutableStateOf<Persona?>(null)
    init{
        viewModelScope.launch(Dispatchers.Default) {
            persona.value = PersonaListApi.getPersona(name)
        }
    }
}