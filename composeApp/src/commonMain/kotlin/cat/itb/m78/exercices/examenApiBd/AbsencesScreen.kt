package cat.itb.m78.exercices.examenApiBd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Absences
import kotlinx.datetime.Clock
import kotlin.math.abs

@Composable
fun Absences(){
    val viewModel = viewModel{ AbsencesScreenViewModel() }
    AbsencesView(
        viewModel.absences.value,
        viewModel.students.value
    )
}

@Composable
fun AbsencesView(
    absences: List<Absences>?,
    students: List<Student>?
){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        if(absences != null && students != null){
            if(absences.isNotEmpty()){
                LazyColumn {
                    items(absences){ absence ->
                        Text("${students[absence.student_id.toInt()-1].name} ${students[absence.student_id.toInt()-1].surnames} ${Clock.System.now()}")
                    }
                }
            }else{
                Text("There is no absences")
            }
        }else{
            CircularProgressIndicator()
        }
    }
}