package com.example.registrojugadores.presentation.logro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.registrojugadores.data.local.entity.LogroEntity

@Composable
fun LogroScreen(
    logro: LogroEntity?,
    agregarLogro: (Int, String, Int?) -> Unit,
    onCancel: () -> Unit
) {
    var jugadorId by remember { mutableStateOf(logro?.jugadorId?.toString() ?: "") }
    var descripcion by remember { mutableStateOf(logro?.descripcion ?: "") }
    var partidaId by remember { mutableStateOf(logro?.partidaId?.toString() ?: "") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE3F2FD), Color(0xFF1565C0))
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
                    text = if (logro == null) "Registrar Logro" else "Editar Logro",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = jugadorId,
                    onValueChange = { jugadorId = it },
                    label = { Text("ID del jugador") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción del logro") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = partidaId,
                    onValueChange = { partidaId = it },
                    label = { Text("ID de la partida (opcional)") },
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
                            val jugadorIdInt = jugadorId.toIntOrNull()
                            val partidaIdInt = partidaId.toIntOrNull()
                            if (jugadorIdInt == null || jugadorIdInt <= 0) {
                                errorMessage = "Debe ingresar un ID de jugador válido."
                            } else if (descripcion.isBlank()) {
                                errorMessage = "Debe ingresar una descripción."
                            } else {
                                errorMessage = null
                                agregarLogro(jugadorIdInt, descripcion, partidaIdInt)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                    ) {
                        if (logro == null) {
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