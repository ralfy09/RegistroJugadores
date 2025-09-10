package com.example.registrojugadores.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.registrojugadores.presentation.DashboardScreen
import com.example.registrojugadores.presentation.jugador.JugadorListScreen
import com.example.registrojugadores.presentation.jugador.JugadorScreen
import com.example.registrojugadores.presentation.jugador.JugadorViewModel

@Composable
fun JugadoresNavHost(
    navHostController: NavHostController,
    jugadorViewModel: JugadorViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = "dashboard"
    ) {

        composable("dashboard") {
            DashboardScreen(navController = navHostController)
        }

        composable("jugadorList") {
            val jugadorList = jugadorViewModel.jugadorList.collectAsState().value

            JugadorListScreen(
                jugadorList = jugadorList,
                onEdit = { jugador ->
                    navHostController.navigate("jugador/${jugador.jugadorId ?: -1}")
                },
                onCreate = {
                    navHostController.navigate("jugador/-1")
                },
                onDelete = { jugador ->
                    jugadorViewModel.delete(jugador)
                }
            )
        }


        composable("jugador/{jugadorId}") { backStackEntry ->
            val jugadorId = backStackEntry.arguments?.getString("jugadorId")?.toIntOrNull()
            val jugador = if (jugadorId != null && jugadorId != -1) {
                jugadorViewModel.getJugadorById(jugadorId)
            } else null

            JugadorScreen(
                jugador = jugador,
                agregarJugador = { nombres, partidas ->
                    if (jugador == null) {
                        jugadorViewModel.agregar(nombres, partidas)
                    } else {
                        jugadorViewModel.update(
                            jugador.copy(nombres = nombres, partidas = partidas)
                        )
                    }
                    navHostController.popBackStack()
                },
                onCancel = { navHostController.popBackStack() }
            )
        }
    }
}
