package cat.itb.m78.exercices.examenApiBd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

object StudentListScreens{
    @Serializable
    data object StudentListScreen
    @Serializable
    data object AbsencesScreen
    @Serializable
    data object StudentAbsencesScreen
}

@Composable
fun StudentListNavigation(){
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomAppBar(
            actions = {
                TextButton(onClick = { navController.navigate(StudentListScreens.StudentListScreen) }, Modifier.fillMaxWidth(0.33f)){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = null
                        )
                        Text("Student List")
                    }
                }
                TextButton(onClick = { navController.navigate(StudentListScreens.AbsencesScreen) }, Modifier.fillMaxWidth(0.5f)){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.Warning,
                            contentDescription = null
                        )
                        Text("Absences")
                    }
                }
                TextButton(onClick = {navController.navigate(StudentListScreens.StudentAbsencesScreen)}, Modifier.fillMaxWidth()){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = null
                        )
                        Text("Students Absences")
                    }
                }
            }
        )
    }) { screenPadding ->
        Column(Modifier.padding(screenPadding)) {
            NavHost(navController = navController, startDestination = StudentListScreens.StudentListScreen){
                composable<StudentListScreens.StudentListScreen>{
                    StudentList()
                }
                composable<StudentListScreens.AbsencesScreen> {
                    Absences()
                }
                composable<StudentListScreens.StudentAbsencesScreen> {
                    StudentAbsences()
                }
            }
        }
    }
}