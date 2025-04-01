package cat.itb.m78.exercices.personaListApp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesScreenViewModel : ViewModel(){
    val personas = mutableStateOf<List<PersonaWithFavorites>?>(null)
    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.Default){
            val personaFromDb = database.personaQueries.selectAll().executeAsList()
            val personaswithfavourites = mutableListOf<PersonaWithFavorites>()
            for(i in personaFromDb){
                personaswithfavourites.add(PersonaWithFavorites(Persona(i.id.toInt(), i.name, i.arcana, i.description, i.image, ""), true))
            }
            personas.value = personaswithfavourites
        }
    }

    fun removeFavourite(persona: PersonaWithFavorites){
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                database.personaQueries.deletePersona(persona.persona.name)
            }
        }
        loadData()
    }
}