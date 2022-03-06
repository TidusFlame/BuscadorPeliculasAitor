package com.example.ejercicioaitorandroid.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeliculaDao {
    @Query("SELECT * From Pelicula ORDER BY title ASC")
    fun obtenerTodas(): LiveData<MutableList<PeliculaClase>>

    @Insert
    suspend fun AnadirPelicula(peliculaClase:PeliculaClase)

    @Query("DELETE FROM pelicula")
    suspend fun borrar():Void

    @Delete
    suspend fun eliminarpelicula(peliculaClase: PeliculaClase):Void

    @Query("SELECT * FROM Pelicula WHERE idPelicula = :id")
    fun IdPelicula(id:Int):PeliculaClase
}