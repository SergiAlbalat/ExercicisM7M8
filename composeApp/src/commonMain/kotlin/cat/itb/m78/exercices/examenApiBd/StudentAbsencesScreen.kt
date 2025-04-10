package cat.itb.m78.exercices.examenApiBd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun StudentAbsences(){
    val viewModel = viewModel{ StudentAbsencesScreenViewModel() }
    StudentAbsencesView(
        viewModel.studentsAbsences.value
    )
}

@Composable
fun StudentAbsencesView(
    students: List<StudentAbsences>?
){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        if(students != null){
            LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                items(students){ student ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("${student.student.name} ${student.student.surnames}")
                        Text(student.student.email)
                        AsyncImage(
                            model = student.student.photo,
                            contentDescription = null
                        )
                        Text("${student.absences} faltes")
                        Spacer(Modifier.height(20.dp))
                    }
                }
            }
        }else{
            CircularProgressIndicator()
        }
    }
}