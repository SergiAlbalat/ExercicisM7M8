package cat.itb.m78.exercices.chemSecure

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorForm(api: ChemSecureApi) {
    var volume by remember { mutableStateOf(50f) }
    var tanks by remember { mutableStateOf<List<Tank>>(emptyList()) }
    var tank by remember { mutableStateOf<Tank?>(null) }
    var isExpanded by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    
    // Load tanks using LaunchedEffect to properly handle lifecycle
    LaunchedEffect(key1 = Unit) {
        try {
            withContext(Dispatchers.IO) {
                val fetchedTanks = api.getTanks()
                withContext(Dispatchers.Main) {
                    tanks = fetchedTanks
                    isLoading = false
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                errorMessage = "Error loading tanks: ${e.message}"
                isLoading = false
            }
        }
    }
    
    Column(modifier = Modifier.padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator()
            Text("Loading tanks...")
        } else if (errorMessage != null) {
            Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
        } else {
            if (tank != null) {
                Text("Volume: ${volume.toInt()}")
                Slider(
                    value = volume,
                    onValueChange = { volume = it },
                    valueRange = 0f..tank!!.capacity.toFloat()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown for tank selection
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it }
            ) {
                TextField(
                    value = if (tank != null) tank!!.id.toString() else "Select a tank",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { 
                        Icon(
                            imageVector = if (isExpanded) 
                                androidx.compose.material.icons.Icons.Filled.KeyboardArrowUp 
                            else 
                                androidx.compose.material.icons.Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Dropdown Arrow"
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    if (tanks.isNotEmpty()) {
                        for (i in tanks) {
                            DropdownMenuItem(
                                text = { Text("Tank ${i.id}") },
                                onClick = {
                                    tank = i
                                    isExpanded = false
                                }
                            )
                        }
                    } else {
                        DropdownMenuItem(
                            text = { Text("No tanks available") },
                            onClick = { isExpanded = false }
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    if (tank != null) {
                        val sensor = Sensor(volume, tank!!.id) // Ya no es necesario toInt() porque id ya es Int
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val success = api.sendSensorData(sensor)
                                println(if (success) "Data sent successfully" else "Error sending data")
                            } catch (e: Exception) {
                                println("Exception sending data: ${e.message}")
                            }
                        }
                    }
                },
                enabled = tank != null
            ) {
                Text("Send")
            }
        }
    }
}