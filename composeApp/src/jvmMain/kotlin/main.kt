
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import java.awt.Dimension
import cat.itb.m78.exercices.App
import cat.itb.m78.exercices.Sensor_ChemSecure.ChemSecure_Api
import cat.itb.m78.exercices.Sensor_ChemSecure.SensorForm

fun main() = application {
    Window(
        title = "M78Exercices",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        val api = ChemSecure_Api()
        window.minimumSize = Dimension(350, 600)
        SensorForm(api)
    }
}

@Composable
fun AppPreview() { App() }