package com.example.registrojugadores.presentation.jugador

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.registrojugadores.data.local.entity.JugadorEntity

@Composable
fun JugadorListScreen(
    jugadorList: List<JugadorEntity>,
    onCreate: () -> Unit,
    onDelete: (JugadorEntity) -> Unit,
    onEdit: (JugadorEntity) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreate,
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE0F2F1),
                            Color(0xFF004D40)
                        )
                    )
                )
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Lista de Jugadores",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFF00796B),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(jugadorList) { jugador ->
                    JugadorRow(jugador, onDelete, onEdit)
                }
            }
        }
    }
}

@Composable
fun JugadorRow(
    jugador: JugadorEntity,
    onDelete: (JugadorEntity) -> Unit,
    onEdit: (JugadorEntity) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Nombre: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = jugador.nombres, fontSize = 16.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Partidas: ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "${jugador.partidas}", fontSize = 16.sp)
                }
            }

            Row {
                IconButton(onClick = { onEdit(jugador) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color(0xFF1976D2))
                }
                IconButton(onClick = { onDelete(jugador) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                }
            }
        }
    }
}
