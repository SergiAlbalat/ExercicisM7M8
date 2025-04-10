package cat.itb.m78.exercices.examenApiBd

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.bd.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class StudentAbsences(val student: Student, val absences: Int)

class StudentAbsencesScreenViewModel : ViewModel() {
    val studentsAbsences = mutableStateOf<List<StudentAbsences>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            val students = StudentListApi.getList()
            if (students != null) {
                val studentsAbsencesmodified = mutableListOf<StudentAbsences>()
                for(i in students){
                    val absences = database.absencesQueries.selectByStudent(i.id.toLong()).executeAsList()
                    studentsAbsencesmodified.add(StudentAbsences(i, absences.size))
                }
                studentsAbsences.value = studentsAbsencesmodified
            }
        }
    }
}