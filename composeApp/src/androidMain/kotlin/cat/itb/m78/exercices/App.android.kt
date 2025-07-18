package cat.itb.m78.exercices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cat.itb.m78.exercices.chemSecure.ChemSecureApi
import cat.itb.m78.exercices.chemSecure.SensorForm

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val api = ChemSecureApi()
        setContent { SensorForm(api) }
    }
}

@Preview
@Composable
fun AppPreview() { App() }
