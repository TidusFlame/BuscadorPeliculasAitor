package com.example.ejercicioaitorandroid.Buscar

import com.example.ejercicioaitorandroid.Database.PeliculaClase
import com.example.ejercicioaitorandroid.Persistencia

class Busqueda {
    suspend fun saveMovie(pelicula: PeliculaClase ) {
        Persistencia.database.peliculaDao()
            .AnadirPelicula(PeliculaClase(pelicula.idPelicula,pelicula.resultType, pelicula.image, pelicula.title, pelicula.description))
    }
}