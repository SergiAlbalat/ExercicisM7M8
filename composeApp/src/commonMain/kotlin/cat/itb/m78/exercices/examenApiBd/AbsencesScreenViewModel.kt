package cat.itb.m78.exercices.examenApiBd

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import cat.itb.m78.exercices.db.Absences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AbsencesScreenViewModel : ViewModel() {
    val absences = mutableStateOf<List<Absences>?>(null)
    val students = mutableStateOf<List<Student>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            absences.value = database.absencesQueries.selectAll().executeAsList()
            students.value = StudentListApi.getList()
        }
    }
}