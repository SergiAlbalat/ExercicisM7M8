package cat.itb.m78.exercices.Stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import org.jetbrains.compose.resources.painterResource

data class Contact(val fullName: String, val email: String, val phone: String)
val contact = Contact("Marta Casserres", "marta@example.com", "934578484")

@Composable
fun Contact(){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Image(
            painter = painterResource(Res.drawable.generatedFace),
            contentDescription = null,
            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(100.dp))
        )
        Text(contact.fullName, fontSize = 2.em, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(40.dp))
        Card{
            Column(modifier = Modifier.padding(10.dp)) {
                Row(modifier = Modifier.padding(15.dp)){
                    Icon(Icons.Default.Email, null)
                    Spacer(Modifier.width(5.dp))
                    Text(contact.email, fontSize = 20.sp)
                }
                Row(modifier = Modifier.padding(15.dp)){
                    Icon(Icons.Default.Call, null)
                    Spacer(Modifier.width(5.dp))
                    Text(contact.phone, fontSize = 20.sp)
                }
            }
        }
    }
}