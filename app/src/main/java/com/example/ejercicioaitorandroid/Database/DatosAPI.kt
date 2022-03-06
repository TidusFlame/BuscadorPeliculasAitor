package com.example.ejercicioaitorandroid.Database

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class DatosAPI (
    var searchType:String,
    var expression:String,
    @Json(name = "results")
    var peliculas:List<PeliculaClase>,
    var errorMessage:String)