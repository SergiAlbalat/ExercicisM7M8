package cat.itb.m78.exercices.chemSecure

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*




@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorForm(api: ChemSecureApi) {
    var volume by remember { mutableStateOf(50f) }
    var tanks by remember { mutableStateOf<List<Tank>?>(null) }
    var tank by remember { mutableStateOf<Tank?>(null) }
    var isExpanded by remember { mutableStateOf(false) }
    CoroutineScope(Dispatchers.Default).launch{
        tanks = api.getTanks()
    }
    Column(modifier = Modifier.padding(16.dp)) {
        if(tank != null){
            Text("Volumen: ${volume}")
            Slider(value = volume, onValueChange = { volume = it }, valueRange = 0f..tank!!.capacity.toFloat())
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {isExpanded = it}
        ){
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {isExpanded = false}
            ){
                if(tanks != null){
                    for(i in tanks!!){
                        DropdownMenuItem(
                            text = {
                                Text(i.id)
                            },
                            onClick = {
                                tank = i
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            val sensor = Sensor(volume, tank!!.id.toInt())

            CoroutineScope(Dispatchers.IO).launch {
                val success = api.sendSensorData(sensor)
                println(if (success) "Datos enviados correctamente" else "Error al enviar datos")
            }
        }) {
            Text("Enviar")
        }
    }
}