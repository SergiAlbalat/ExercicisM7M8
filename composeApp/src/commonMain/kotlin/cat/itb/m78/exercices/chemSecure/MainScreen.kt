@file:OptIn(ExperimentalMaterial3Api::class)

package cat.itb.m78.exercices.Sensor_ChemSecure

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




@Composable
fun SensorForm(api: ChemSecure_Api) {
    var temperature by remember { mutableStateOf(25f) }
    var volume by remember { mutableStateOf(50f) }
    var id by remember { mutableStateOf(1f) }
    var tanks by remember { mutableStateOf<List<Tank>?>(null) }
    var tank by remember { mutableStateOf<Tank?>(null) }
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Temperatura: ${temperature.toInt()}°C")
        Slider(value = temperature, onValueChange = { temperature = it }, valueRange = 0f..100f)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Volumen: ${volume}")
        Slider(value = volume, onValueChange = { volume = it }, valueRange = 0f..100f)

        Spacer(modifier = Modifier.height(16.dp))

        Text("ID: ${id.toInt()}")
        Slider(value = id, onValueChange = { id = it }, valueRange = 1f..100f, steps = 99)

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
            val sensor = Sensor(temperature.toInt(), volume, id.toInt())

            CoroutineScope(Dispatchers.IO).launch {
                val success = api.sendSensorData(sensor)
                println(if (success) "Datos enviados correctamente" else "Error al enviar datos")
            }
        }) {
            Text("Enviar")
        }
    }
}
