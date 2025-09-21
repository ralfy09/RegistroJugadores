package com.example.registrojugadores.presentation.logro

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrojugadores.data.local.entity.LogroEntity
import com.example.registrojugadores.data.repository.LogroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class LogroViewModel @Inject constructor(
    private val logroRepository: LogroRepository
) : ViewModel() {

    private val _logroList = MutableStateFlow<List<LogroEntity>>(emptyList())
    val logroList: StateFlow<List<LogroEntity>> get() = _logroList

    init {
        loadLogros()
    }

    private fun loadLogros() {
        viewModelScope.launch {
            logroRepository.getAll().collect { lista ->
                Log.d("LogroViewModel", "Lista recibida: ${lista.size}")
                _logroList.value = lista
            }
        }
    }

    fun saveLogro(logro: LogroEntity) {
        viewModelScope.launch {
            logroRepository.saveLogro(logro)
            loadLogros()
        }
    }

    fun agregar(jugadorId: Int, descripcion: String, partidaId: Int? = null) {
        val logro = LogroEntity(
            logroId = null,
            fecha = Date(),
            jugadorId = jugadorId,
            partidaId = partidaId,
            descripcion = descripcion
        )
        saveLogro(logro)
    }

    fun delete(logro: LogroEntity) {
        viewModelScope.launch {
            logroRepository.delete(logro)
            loadLogros()
        }
    }

    fun getLogroById(id: Int?): LogroEntity? {
        return _logroList.value.find { it.logroId == id }
    }
}