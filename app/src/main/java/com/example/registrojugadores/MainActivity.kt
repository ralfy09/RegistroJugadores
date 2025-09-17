package com.example.registrojugadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.registrojugadores.data.repository.JugadorRepository
import com.example.registrojugadores.presentation.jugador.JugadorViewModel
import com.example.registrojugadores.presentation.navigation.JugadoresNavHost
import com.example.registrojugadores.presentation.partida.PartidaViewModel
import com.example.registrojugadores.ui.theme.RegistroJugadoresTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RegistroJugadoresTheme {
                val navController = rememberNavController()
                val jugadorViewModel: JugadorViewModel = hiltViewModel()
                val partidaViewModel: PartidaViewModel = hiltViewModel()

                JugadoresNavHost(
                    navHostController = navController,
                    jugadorViewModel = jugadorViewModel,
                    partidaViewModel = partidaViewModel
                )
            }
        }
    }
}