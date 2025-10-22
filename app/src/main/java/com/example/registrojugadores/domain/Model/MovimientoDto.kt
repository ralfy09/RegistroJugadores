package com.example.registrojugadores.domain.Model

data class MovimientoDto(
    val movimientoId: Int,
    val jugador: String,
    val posicionFila: Int,
    val posicionColumna: Int
)