package cat.itb.m78.exercices.personaListApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonaListDetailedScreenViewModel(name: String) : ViewModel(){
    val persona = mutableStateOf<PersonaWithFavorites?>(null)
    private val personaName = name
    init{
        loadData()
    }
    private fun loadData(){
        viewModelScope.launch(Dispatchers.Default) {
            val personaapi = PersonaListApi.getPersona(personaName)
            if(database.personaQueries.selectOne(personaapi!!.name).executeAsOneOrNull() != null){
                persona.value = PersonaWithFavorites(personaapi, true)
            }else{
                persona.value = PersonaWithFavorites(personaapi, false)
            }
        }
    }
    fun addFavourite(persona: PersonaWithFavorites){
        if(!persona.isFab){
            viewModelScope.launch {
                withContext(Dispatchers.Default){
                    database.personaQueries.savePersona(persona.persona.name, persona.persona.arcana, persona.persona.description, persona.persona.image, persona.persona.apiName)
                }
            }
        }else{
            viewModelScope.launch {
                withContext(Dispatchers.Default){
                    database.personaQueries.deletePersona(persona.persona.name)
                }
            }
        }
        loadData()
    }
}