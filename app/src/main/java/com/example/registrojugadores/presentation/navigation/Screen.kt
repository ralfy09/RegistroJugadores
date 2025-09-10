package com.example.registrojugadores.presentation.navigation

import kotlinx.serialization.Serializable
sealed class Screen {
    @Serializable
    object DashboardScreen : Screen()

    @Serializable
    object JugadorList : Screen()

    @Serializable
    data class Jugador(val jugadorId: Int) : Screen()

    @Serializable
    data class EditJugador(val jugadorId: Int) : Screen()

    @Serializable
    data class DeleteJugador(val jugadorId: Int) : Screen()
}
