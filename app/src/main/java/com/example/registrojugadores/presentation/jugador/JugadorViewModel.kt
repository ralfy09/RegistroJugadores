package com.example.registrojugadores.presentation.jugador
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrojugadores.data.local.entity.JugadorEntity
import com.example.registrojugadores.data.repository.JugadorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadorViewModel @Inject constructor(
    private val jugadoresRepository: JugadorRepository
) : ViewModel() {

    private val _jugadorList = MutableStateFlow<List<JugadorEntity>>(emptyList())
    val jugadorList: StateFlow<List<JugadorEntity>> get() = _jugadorList

    init {
        loadJugadores()
    }

    private fun loadJugadores() {
        viewModelScope.launch {
            jugadoresRepository.getAll().collect { lista ->
                Log.d("JugadorViewModel", "Lista recibida: ${lista.size}")
                _jugadorList.value = lista
            }
        }
    }

    fun saveJugador(jugador: JugadorEntity) {
        viewModelScope.launch {
            jugadoresRepository.saveJugador(jugador)
            loadJugadores()
        }
    }

    fun agregar(nombres: String, partidas: Int) {
        val jugador = JugadorEntity(
            jugadorId = null,
            nombres = nombres,
            partidas = partidas
        )
        saveJugador(jugador)
    }

    fun update(jugador: JugadorEntity) {
        saveJugador(jugador)
    }

    fun delete(jugador: JugadorEntity) {
        viewModelScope.launch {
            jugadoresRepository.delete(jugador)
            loadJugadores()
        }
    }

    fun getJugadorById(id: Int?): JugadorEntity? {
        return _jugadorList.value.find { it.jugadorId == id }
    }
}
