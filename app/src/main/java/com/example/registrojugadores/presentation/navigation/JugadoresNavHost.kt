package com.example.registrojugadores.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.registrojugadores.presentation.DashboardScreen
import com.example.registrojugadores.presentation.jugador.JugadorListScreen
import com.example.registrojugadores.presentation.jugador.JugadorScreen
import com.example.registrojugadores.presentation.jugador.JugadorViewModel
import com.example.registrojugadores.presentation.partida.EditPartidaScreen
import com.example.registrojugadores.presentation.partida.PartidaListScreen
import com.example.registrojugadores.presentation.partida.PartidaScreen
import com.example.registrojugadores.presentation.partida.PartidaViewModel

@Composable
fun JugadoresNavHost(
    navHostController: NavHostController,
    jugadorViewModel: JugadorViewModel,
    partidaViewModel: PartidaViewModel
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

        composable("partidaList") {
            val partidaViewModel: PartidaViewModel = hiltViewModel()
            val jugadorViewModel: JugadorViewModel = hiltViewModel()

            PartidaListScreen(
                partidaViewModel = partidaViewModel,
                jugadorViewModel = jugadorViewModel,
                onEdit = { partida ->
                    navHostController.navigate("partida/edit/${partida.partidaId}")
                },
                onCreate = {
                    navHostController.navigate("partida/null")
                }
            )
        }

        composable("partida/edit/{partidaId}") { backStackEntry ->
            val partidaIdArg = backStackEntry.arguments?.getString("partidaId")
            val partidaId = partidaIdArg?.toIntOrNull() ?: 0
            val partidaViewModel: PartidaViewModel = hiltViewModel()
            val jugadorViewModel: JugadorViewModel = hiltViewModel()

            EditPartidaScreen(
                navController = navHostController,
                partidaId = partidaId,
                partidaViewModel = partidaViewModel,
                jugadorViewModel = jugadorViewModel,
                onCancel = { navHostController.popBackStack() }
            )
        }

        composable("partida/{partidaId}") { backStackEntry ->
            val partidaIdArg = backStackEntry.arguments?.getString("partidaId")
            val partidaId = partidaIdArg?.toIntOrNull()
            val partidaViewModel: PartidaViewModel = hiltViewModel()
            val jugadorViewModel: JugadorViewModel = hiltViewModel()

            PartidaScreen(
                navController = navHostController,
                partidaId = partidaId,
                partidaViewModel = partidaViewModel,
                jugadorViewModel = jugadorViewModel,
                onCancel = { navHostController.popBackStack() }
            )
        }
    }
    }