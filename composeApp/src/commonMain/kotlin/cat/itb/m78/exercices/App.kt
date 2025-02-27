package cat.itb.m78.exercices


import androidx.compose.runtime.*
import cat.itb.m78.exercices.api.CountriesApp
import cat.itb.m78.exercices.api.JokesApp
import cat.itb.m78.exercices.examen.CalculadoraNavegation
import cat.itb.m78.exercices.settings.CountViewsApp
import cat.itb.m78.exercices.settings.RememberMyNameApp
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    CountriesApp()
}
