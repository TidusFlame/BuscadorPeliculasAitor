package com.example.ejercicioaitorandroid.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PeliculaClase::class), version = 2, exportSchema = false)
abstract class PeliculaDatabase: RoomDatabase() {
    abstract fun peliculaDao():PeliculaDao
}