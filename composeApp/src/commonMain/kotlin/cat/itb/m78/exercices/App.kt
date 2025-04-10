package cat.itb.m78.exercices


import androidx.compose.runtime.*
import cat.itb.m78.exercices.State.SayHello
import cat.itb.m78.exercices.api.CountriesApp
import cat.itb.m78.exercices.api.EstatEmbassamentsNavegation
import cat.itb.m78.exercices.api.JokesApp
import cat.itb.m78.exercices.bd.MessagesApp
import cat.itb.m78.exercices.examen.CalculadoraNavegation
import cat.itb.m78.exercices.examenApiBd.StudentListNavigation
import cat.itb.m78.exercices.personaListApp.PersonaListNavegation
import cat.itb.m78.exercices.settings.CountViewsApp
import cat.itb.m78.exercices.settings.RememberMyNameApp
import cat.itb.m78.exercices.theme.AppTheme
import cat.itb.m78.exercices.trivia.TriviaNavegation

@Composable
internal fun App() = AppTheme {
    StudentListNavigation()
}
