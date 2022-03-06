package com.example.ejercicioaitorandroid

import android.app.Application
import androidx.room.Room
import com.example.ejercicioaitorandroid.Database.PeliculaDatabase

class Persistencia : Application() {

    companion object {
        lateinit var database: PeliculaDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(this, PeliculaDatabase::class.java,getString(R.string.basededatos))
            .build()
    }
}