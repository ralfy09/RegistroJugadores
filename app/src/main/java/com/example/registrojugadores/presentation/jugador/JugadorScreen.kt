package com.example.registrojugadores.presentation.jugador

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registrojugadores.data.local.entity.JugadorEntity

@Composable
fun JugadorScreen(
    jugador: JugadorEntity?,
    agregarJugador: (String, Int) -> Unit,
    onCancel: () -> Unit
) {
    var nombres by remember { mutableStateOf(jugador?.nombres ?: "") }
    var partidas by remember { mutableStateOf(jugador?.partidas?.toString() ?: "") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE0F2F1), Color(0xFF004D40))
                )
            )
            .padding(16.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (jugador == null) "Registrar Jugador" else "Editar Jugador",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombres,
                    onValueChange = { nombres = it },
                    label = { Text("Nombre del jugador") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = partidas,
                    onValueChange = { partidas = it },
                    label = { Text("Partidas") },
                    placeholder = { Text("0") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Cancelar")
                    }

                    Button(
                        onClick = {
                            val partidasInt = partidas.toIntOrNull()
                            if (nombres.isBlank()) {
                                errorMessage = "El nombre no puede estar vacío."
                            } else if (partidasInt == null || partidasInt < 0) {
                                errorMessage = "Las partidas deben ser un número válido mayor o igual a 0."
                            } else {
                                errorMessage = null
                                agregarJugador(nombres, partidasInt)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                    ) {
                        if (jugador == null) {
                            Icon(Icons.Default.CheckCircle, contentDescription = "Guardar")
                            Spacer(Modifier.width(8.dp))
                            Text("Guardar")
                        } else {
                            Icon(Icons.Default.Edit, contentDescription = "Actualizar")
                            Spacer(Modifier.width(8.dp))
                            Text("Actualizar")
                        }
                    }
                }
            }
        }
    }
}
