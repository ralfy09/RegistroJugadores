package com.example.registrojugadores.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jugadores")
data class JugadorEntity(
    @PrimaryKey(autoGenerate = true)
    val jugadorId: Int? = null,
    val nombres: String = "",
    val partidas:Int=0
)