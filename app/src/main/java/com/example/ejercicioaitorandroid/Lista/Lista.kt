package com.example.ejercicioaitorandroid.Lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.ejercicioaitorandroid.Database.PeliculaClase
import com.example.ejercicioaitorandroid.Persistencia

class Lista {
    val peliculas: LiveData<MutableList<PeliculaClase>> = liveData {
        val pelisLiveData = Persistencia.database.peliculaDao().obtenerTodas()
        emitSource(pelisLiveData.map { peliculas->
            peliculas.sortedBy { it.title }.toCollection(mutableListOf())
        })
    }
    suspend fun borrar() {
        Persistencia.database.peliculaDao().borrar()
    }
    suspend fun eliminarpelicula(pelicula: PeliculaClase) {
        Persistencia.database.peliculaDao().eliminarpelicula(pelicula)
    }
}