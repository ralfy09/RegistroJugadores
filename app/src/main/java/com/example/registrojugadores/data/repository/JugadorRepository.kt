package com.example.registrojugadores.data.repository

import com.example.registrojugadores.data.local.dao.JugadorDao
import com.example.registrojugadores.data.local.entity.JugadorEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JugadorRepository @Inject constructor(
    private val dao: JugadorDao
) {
    suspend fun saveJugador(jugador: JugadorEntity): Boolean {

        val existe = dao.existeNombre(jugador.nombres)
        if (existe > 0) return false

        dao.save(jugador)
        return true
    }

    suspend fun find(id: Int): JugadorEntity? = dao.find(id)

    suspend fun delete(jugador: JugadorEntity) = dao.delete(jugador)

    fun getAll(): Flow<List<JugadorEntity>> = dao.getAll()
}
