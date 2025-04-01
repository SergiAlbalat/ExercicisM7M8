package cat.itb.m78.exercices.personaListApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class PersonaWithFavorites(val persona: Persona, val isFab : Boolean)

class GeneralScreenViewModel : ViewModel(){
    val personas = mutableStateOf<List<PersonaWithFavorites>?>(null)
    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.Default){
            val apiList = PersonaListApi.getList()
            val personaswithfavourites = mutableListOf<PersonaWithFavorites>()
            for(i in apiList!!){
                if(database.personaQueries.selectOne(i.name).executeAsOneOrNull() != null){
                    personaswithfavourites.add(PersonaWithFavorites(i, true))
                }else{
                    personaswithfavourites.add(PersonaWithFavorites(i, false))
                }
            }
            personas.value = personaswithfavourites
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