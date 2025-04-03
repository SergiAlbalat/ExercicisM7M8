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
    val searchInput = mutableStateOf("")
    init {
        loadData()
    }

    fun searchInputChange(it: String){
        searchInput.value = it
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.Default){
            val personaFromDb = database.personaQueries.selectAll().executeAsList().filter { it.name.startsWith(searchInput.value, ignoreCase = true) }
            val personaswithfavourites = mutableListOf<PersonaWithFavorites>()
            for(i in personaFromDb){
                personaswithfavourites.add(PersonaWithFavorites(Persona(i.id.toInt(), i.name, i.arcana, i.description, i.image, i.apiName), true))
            }
            personas.value = personaswithfavourites
        }
    }

    fun removeFavourite(persona: PersonaWithFavorites){
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                database.personaQueries.deletePersona(persona.persona.name)
                loadData()
            }
        }
    }
}