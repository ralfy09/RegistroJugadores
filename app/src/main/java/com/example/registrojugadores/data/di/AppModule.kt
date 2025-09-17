package com.example.registrojugadores.data.di

import android.content.Context
import androidx.room.Room
import com.example.registrojugadores.data.local.dao.JugadorDao
import com.example.registrojugadores.data.local.dao.PartidaDao
import com.example.registrojugadores.data.local.database.JugadorDb
import com.example.registrojugadores.data.repository.JugadorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): JugadorDb =
        Room.databaseBuilder(context,JugadorDb::class.java, "jugador.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideJugadorDao(db: JugadorDb): JugadorDao = db.jugadorDao()

    @Provides
    @Singleton
    fun providePartidaDao(db: JugadorDb): PartidaDao = db.PartidaDao()

    @Provides
    @Singleton
    fun provideJugadoresRepository(dao: JugadorDao): JugadorRepository {
        return JugadorRepository(dao)
    }
}