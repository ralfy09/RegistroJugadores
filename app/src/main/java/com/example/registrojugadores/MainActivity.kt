package com.example.registrojugadores
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.registrojugadores.data.local.database.JugadorDb
import com.example.registrojugadores.data.repository.JugadorRepository
import com.example.registrojugadores.presentation.jugador.JugadorViewModel
import com.example.registrojugadores.presentation.navigation.JugadoresNavHost
import com.example.registrojugadores.ui.theme.RegistroJugadoresTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = Room.databaseBuilder(
            applicationContext,
            JugadorDb::class.java,
            "Jugador.db"
        ).fallbackToDestructiveMigration()
            .build()

        val jugadorRepository = JugadorRepository(database.jugadorDao())
        val jugadorViewModel = JugadorViewModel(jugadorRepository)

        setContent {
            RegistroJugadoresTheme {
                val navController = rememberNavController()
                JugadoresNavHost(
                    navHostController = navController,
                    jugadorViewModel = jugadorViewModel
                )
            }
        }
    }
}
