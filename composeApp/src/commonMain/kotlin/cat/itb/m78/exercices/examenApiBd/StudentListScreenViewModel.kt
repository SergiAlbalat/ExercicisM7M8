package cat.itb.m78.exercices.examenApiBd

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

class StudentListScreenViewModel : ViewModel() {
    val students = mutableStateOf<List<Student>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            students.value = StudentListApi.getList()
        }
    }

    fun saveAbsence(student: Student){
        database.absencesQueries.insertOne(student.id.toLong(), LocalDateTime.toString())
    }
}