package cat.itb.m78.exercices.camara

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage

@Composable
fun Gallery(photos : List<String>, navigateToCamara: ()->Unit){
    Column {
        Button(onClick = navigateToCamara) {
            Text("Camara")
        }
        LazyColumn {
            items(photos){ photo ->
                AsyncImage(
                    model = photo,
                    contentDescription = null
                )
            }
        }
    }
}