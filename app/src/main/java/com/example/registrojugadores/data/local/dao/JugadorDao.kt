package com.example.registrojugadores.data.local.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.registrojugadores.data.local.entity.JugadorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JugadorDao {
    @Upsert
    suspend fun save(jugador: JugadorEntity)

    @Query("SELECT * FROM Jugadores WHERE jugadorId = :id LIMIT 1")
    suspend fun find(id: Int): JugadorEntity?

    @Delete
    suspend fun delete(jugador: JugadorEntity)

    @Query("SELECT * FROM Jugadores")
    fun getAll(): Flow<List<JugadorEntity>>

    @Query("SELECT COUNT(*) FROM Jugadores WHERE nombres = :nombres")
    suspend fun existeNombre(nombres: String): Int
}