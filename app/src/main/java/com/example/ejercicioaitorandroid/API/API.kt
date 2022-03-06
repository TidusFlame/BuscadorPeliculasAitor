package com.example.ejercicioaitorandroid.API

import com.example.ejercicioaitorandroid.Database.DatosAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("SearchMovie/k_gy9itqdc/{expression}")
    suspend fun getMovies( @Path("expression")expression:String):DatosAPI
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
val retrofitMoshi = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://imdb-api.com/en/API/")
    .build()
object APIMoshi {
    val retrofitMoshiService: API by lazy {
        retrofitMoshi.create(API::class.java)
    }
}