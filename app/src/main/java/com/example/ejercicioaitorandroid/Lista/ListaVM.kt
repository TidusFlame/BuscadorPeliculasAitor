package com.example.ejercicioaitorandroid.Lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicioaitorandroid.Database.PeliculaClase
import kotlinx.coroutines.launch

class ListaVM : ViewModel() {
    private var model:Lista = Lista()
    private val peliculas = model.peliculas

    fun obtenerTodos(): LiveData<MutableList<PeliculaClase>> {
        return peliculas;
    }
    fun  eliminarpelicula(pelicula: PeliculaClase){
        viewModelScope.launch {
            model.eliminarpelicula(pelicula)

        }
    }
    fun borrado() {
        viewModelScope.launch {
            viewModelScope.launch {
                model.borrar()
            }
        }
    }
}