package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.Avançats.OteloApp
import cat.itb.m78.exercices.Navegation.ManualNav
import cat.itb.m78.exercices.Navegation.MenuApp
import cat.itb.m78.exercices.State.DiceRoller
import cat.itb.m78.exercices.State.GoodMorningAndNight
import cat.itb.m78.exercices.State.SayHello
import cat.itb.m78.exercices.State.SecretNumber
import cat.itb.m78.exercices.Stateless.Contact
import cat.itb.m78.exercices.Stateless.HelloWorld
import cat.itb.m78.exercices.Stateless.MessagesList
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import cat.itb.m78.exercices.ViewModel.CounterApp
import cat.itb.m78.exercices.ViewModel.ShoppingListApp
import kotlinx.serialization.Serializable

object Destinaton{
    @Serializable
    data object MainMenu
    @Serializable
    data object Stateless
    @Serializable
    data object State
    @Serializable
    data object ViewModel
    @Serializable
    data object Navegation
    @Serializable
    data object Avancats
    @Serializable
    data object Contact
    @Serializable
    data object HelloWorld
    @Serializable
    data object MessageList
    @Serializable
    data object Resource
    @Serializable
    data object Welcome
    @Serializable
    data object DiceRoller
    @Serializable
    data object GoodMorningAndNight
    @Serializable
    data object SayHello
    @Serializable
    data object SecretNumber
    @Serializable
    data object Counter
    @Serializable
    data object ShoppingList
    @Serializable
    data object Pages
    @Serializable
    data object Otelo
}

@Composable
fun Navegation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinaton.MainMenu){
        composable<Destinaton.MainMenu>{
            MainNavegationMenuApp(
                {navController.navigate(Destinaton.Stateless)},
                {navController.navigate(Destinaton.State)},
                {navController.navigate(Destinaton.ViewModel)},
                {navController.navigate(Destinaton.Navegation)},
                {navController.navigate(Destinaton.Avancats)}
            )
        }
        composable<Destinaton.Stateless>{
            StatelessMenuApp(
                {navController.navigate(Destinaton.Contact)},
                {navController.navigate(Destinaton.HelloWorld)},
                {navController.navigate(Destinaton.MessageList)},
                {navController.navigate(Destinaton.Resource)},
                {navController.navigate(Destinaton.Welcome)}
            )
        }
        composable<Destinaton.State>{
            StateMenuApp(
                {navController.navigate(Destinaton.DiceRoller)},
                {navController.navigate(Destinaton.GoodMorningAndNight)},
                {navController.navigate(Destinaton.SayHello)},
                {navController.navigate(Destinaton.SecretNumber)}
            )
        }
        composable<Destinaton.ViewModel>{
            ViewModelMenuApp(
                {navController.navigate(Destinaton.Counter)},
                {navController.navigate(Destinaton.ShoppingList)}
            )
        }
        composable<Destinaton.Navegation>{
            NavegationMenuApp(
                {navController.navigate(Destinaton.Pages)}
            )
        }
        composable<Destinaton.Avancats>{
            AvancatsMenuApp(
                {navController.navigate(Destinaton.Otelo)}
            )
        }
        composable<Destinaton.Contact>{ Contact() }
        composable<Destinaton.HelloWorld>{ HelloWorld() }
        composable<Destinaton.MessageList> { MessagesList() }
        composable<Destinaton.Resource> { Resource() }
        composable<Destinaton.Welcome> { Welcome() }
        composable<Destinaton.DiceRoller> { DiceRoller() }
        composable<Destinaton.GoodMorningAndNight> { GoodMorningAndNight() }
        composable<Destinaton.SayHello> { SayHello() }
        composable<Destinaton.SecretNumber> { SecretNumber() }
        composable<Destinaton.Counter> { CounterApp() }
        composable<Destinaton.ShoppingList> { ShoppingListApp() }
        composable<Destinaton.Pages> { ManualNav() }
        composable<Destinaton.Otelo>{ OteloApp() }
    }
}

@Composable
fun MainNavegationMenuApp(
    navegateToStatelessMenu: ()->Unit,
    navegateToStateMenu: ()->Unit,
    navegateToViewModelMenu: ()->Unit,
    navegateToNavegationMenu: ()->Unit,
    navegateToAvancatsMenu: ()->Unit
){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = navegateToStatelessMenu){
            Text("StatelessExercises")
        }
        Button(onClick = navegateToStateMenu){
            Text("StateExercises")
        }
        Button(onClick = navegateToViewModelMenu){
            Text("ViewModelExercises")
        }
        Button(onClick = navegateToNavegationMenu){
            Text("NavegationExercises")
        }
        Button(onClick = navegateToAvancatsMenu){
            Text("AcançatsExercises")
        }
    }
}

@Composable
fun StatelessMenuApp(
    navegateToContact: ()->Unit,
    navegateToHelloWorld: ()->Unit,
    navegateToMessagesList: ()->Unit,
    navegateToResource: ()->Unit,
    navegateToWelcome: ()->Unit
){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = navegateToHelloWorld){
            Text("HelloWorld")
        }
        Button(onClick = navegateToWelcome){
            Text("Welcome")
        }
        Button(onClick = navegateToResource){
            Text("Resources")
        }
        Button(onClick = navegateToContact){
            Text("Contact")
        }
        Button(onClick = navegateToMessagesList){
            Text("MessagesList")
        }
    }
}

@Composable
fun StateMenuApp(
    navegateToDiceRoller: ()->Unit,
    navegateToGoodMorningAndNight: ()->Unit,
    navegateToSayHello: ()->Unit,
    navegateToSecretNumber: ()->Unit
){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = navegateToGoodMorningAndNight){
            Text("GoodMorning")
        }
        Button(onClick = navegateToSecretNumber){
            Text("SecretNumber")
        }
        Button(onClick = navegateToSayHello){
            Text("SayHello")
        }
        Button(onClick = navegateToDiceRoller){
            Text("DiceRoller")
        }
    }
}

@Composable
fun ViewModelMenuApp(
    navegateToCounter: ()->Unit,
    navegateToShoppingList: ()->Unit
){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = navegateToCounter){
            Text("Counter")
        }
        Button(onClick = navegateToShoppingList){
            Text("ShoppingList")
        }
    }
}

@Composable
fun NavegationMenuApp(
    navegateToPages: ()->Unit
){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = navegateToPages){
            Text("ManualNav")
        }
    }
}

@Composable
fun AvancatsMenuApp(
    navegateToOtelo: ()->Unit
){
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Button(onClick = navegateToOtelo){
            Text("Otelo")
        }
    }
}