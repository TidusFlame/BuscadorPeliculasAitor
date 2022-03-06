package com.example.ejercicioaitorandroid.Buscar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicioaitorandroid.API.APIMoshi
import com.example.ejercicioaitorandroid.Database.DatosAPI
import com.example.ejercicioaitorandroid.Database.PeliculaClase
import kotlinx.coroutines.launch

class BuscaVM:ViewModel() {
    private val _response = MutableLiveData<DatosAPI>()
    val response: LiveData<DatosAPI>
        get() = _response
    private  var model:Busqueda = Busqueda()


    fun searchResults(filter:String) {
        viewModelScope.launch {
            _response.value = APIMoshi.retrofitMoshiService.getMovies(filter)
        }
    }

    fun saveFilm(pelicula: PeliculaClase) {
        viewModelScope.launch {
            model.saveMovie(pelicula)
        }
    }
}