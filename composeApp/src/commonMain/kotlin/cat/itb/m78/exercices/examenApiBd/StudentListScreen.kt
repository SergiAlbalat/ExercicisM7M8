package cat.itb.m78.exercices.examenApiBd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun StudentList(){
    val viewModel = viewModel{ StudentListScreenViewModel() }
    StudentListView(
        viewModel.students.value,
        viewModel::saveAbsence
    )
}

@Composable
fun StudentListView(
    students: List<Student>?,
    saveAbsence: (Student) -> Unit
){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        if(students != null){
            LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                items(students){ student ->
                    TextButton(onClick = {saveAbsence(student)}, Modifier.size(300.dp)){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("${student.name} ${student.surnames}")
                            Text(student.email)
                            AsyncImage(
                                model = student.photo,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }else{
            CircularProgressIndicator()
        }
    }
}