package cat.itb.m78.exercices.Navegation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

sealed interface Screen{
    data object Menu : Screen
    data object Screen1 : Screen
    data object Screen2 : Screen
    data class Screen3(val salutation: Boolean) : Screen
}

class NavegationViewModel : ViewModel(){
    val currentScreen = mutableStateOf<Screen>(Screen.Menu)
    fun navigateTo(screen: Screen){
        currentScreen.value = screen
    }
}

@Composable
fun ManualNav(){
    val viewModel = viewModel{NavegationViewModel()}
    val currentScreen = viewModel.currentScreen.value
    when(currentScreen){
        Screen.Menu -> MenuApp({viewModel.navigateTo(Screen.Screen1)},{viewModel.navigateTo(Screen.Screen2)},{viewModel.navigateTo(Screen.Screen3(it))})
        Screen.Screen1 -> Screen1App({viewModel.navigateTo(Screen.Menu)})
        Screen.Screen2 -> Screen2App({viewModel.navigateTo(Screen.Menu)})
        is Screen.Screen3 -> Screen3App(currentScreen.salutation, {viewModel.navigateTo(Screen.Menu)})
    }
}

class MenuViewModel : ViewModel(){

}

@Composable
fun MenuApp(navegateToScreen1: ()->Unit, navegateToScreen2: () -> Unit, navegateToScreen3: (Boolean) -> Unit){
    val viewModel = viewModel{MenuViewModel()}
    MenuView(navegateToScreen1, navegateToScreen2, navegateToScreen3)
}

@Composable
fun MenuView(navegateToScreen1: () -> Unit, navegateToScreen2: () -> Unit, navegateToScreen3: (Boolean) -> Unit){
    Column(Modifier.fillMaxSize()) {
        Button(onClick = navegateToScreen1){
            Text("Screen 1")
        }
        Button(onClick = navegateToScreen2){
            Text("Screen 2")
        }
        Button(onClick = {navegateToScreen3(true)}){
            Text("Screen 3 - Hello")
        }
        Button(onClick = {navegateToScreen3(false)}){
            Text("Screen 3 - Bye")
        }
    }
}

class Screen1ViewModel : ViewModel(){

}

@Composable
fun Screen1App(navegateToMenu: ()->Unit){
    val viewModel = viewModel{Screen1ViewModel()}
    Screen1View(navegateToMenu)
}

@Composable
fun Screen1View(navegateToMenu: () -> Unit){
    Column(modifier = Modifier.fillMaxSize().background(Color.Green), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
        Text("Screen 1")
        Button(onClick = navegateToMenu){
            Text("Main Menu")
        }
    }
}

class Screen2ViewModel : ViewModel(){

}

@Composable
fun Screen2App(navegateToMenu: ()->Unit){
    val viewModel = viewModel{Screen2ViewModel()}
    Screen2View(navegateToMenu)
}

@Composable
fun Screen2View(navegateToMenu: () -> Unit){
    Column(modifier = Modifier.fillMaxSize().background(Color.Red), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Screen 2")
        Button(onClick = navegateToMenu){
            Text("Main Menu")
        }
    }
}

class Screen3ViewModel : ViewModel(){

}

@Composable
fun Screen3App(salutation: Boolean, navegateToMenu: ()->Unit){
    val viewModel = viewModel{Screen3ViewModel()}
    Screen3View(salutation, navegateToMenu)
}

@Composable
fun Screen3View(salutation: Boolean, navegateToMenu: () -> Unit){
    Column(modifier = Modifier.fillMaxSize().background(Color.Blue), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Screen 3")
        if(salutation){
            Text("Hello")
        }else{
            Text("Bye")
        }
        Button(onClick = navegateToMenu){
            Text("Main Menu")
        }
    }
}