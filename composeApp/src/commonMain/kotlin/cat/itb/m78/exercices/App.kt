package cat.itb.m78.exercices


import androidx.compose.runtime.*
import cat.itb.m78.exercices.trivia.TriviaNavegation
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    TriviaNavegation()
}
