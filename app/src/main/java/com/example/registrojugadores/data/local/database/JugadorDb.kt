package com.example.registrojugadores.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.registrojugadores.data.local.dao.JugadorDao
import com.example.registrojugadores.data.local.dao.PartidaDao
import com.example.registrojugadores.data.local.entity.JugadorEntity
import com.example.registrojugadores.data.local.entity.PartidaEntity


@Database(
    entities = [JugadorEntity::class, PartidaEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun jugadorDao(): JugadorDao

    abstract fun PartidaDao (): PartidaDao
}