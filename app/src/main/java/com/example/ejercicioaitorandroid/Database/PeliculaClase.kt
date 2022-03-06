package com.example.ejercicioaitorandroid.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "Pelicula")
data class PeliculaClase (
    @PrimaryKey(autoGenerate = true)
    var idPelicula: Int = 0,
    var resultType: String,
    var image: String,
    var title: String,
    var description: String
    )