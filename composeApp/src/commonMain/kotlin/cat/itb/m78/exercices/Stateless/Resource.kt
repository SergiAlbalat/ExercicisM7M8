package cat.itb.m78.exercices.Stateless

import androidx.annotation.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Example
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Resource(){
    Column {
        Text(stringResource(Res.string.Example), fontSize = 2.em)
        Image(
            painter = painterResource(Res.drawable.generatedFace),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}