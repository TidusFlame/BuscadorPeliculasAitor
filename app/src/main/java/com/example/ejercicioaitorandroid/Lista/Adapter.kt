package com.example.ejercicioaitorandroid.Lista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioaitorandroid.Database.PeliculaClase
import com.example.ejercicioaitorandroid.R
import com.example.ejercicioaitorandroid.databinding.FichaBinding

class Adapter (private var listener:OnclickListener):
    ListAdapter<PeliculaClase, RecyclerView.ViewHolder>(MovieDiffCallBack()) {
    private lateinit var _Context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        _Context = parent.context
        val view = LayoutInflater.from(_Context).inflate(R.layout.ficha,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pelicula = getItem(position)
        with(holder as ViewHolder) {
            setListener(pelicula)
            "${pelicula.title}\n${pelicula.description}".also {
                binding.info.text = it
            }

            com.bumptech.glide.Glide.with(_Context)
                .load(pelicula.image)
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(binding.movieimg)

        }
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = FichaBinding.bind(view)
        fun setListener(pelicula:PeliculaClase) {
            with(binding.root) {
                setOnClickListener { listener.onDelete(pelicula) }
            }
        }
    }
}
class MovieDiffCallBack : DiffUtil.ItemCallback<PeliculaClase>() {
    override fun areItemsTheSame(oldItem: PeliculaClase, newItem: PeliculaClase): Boolean {
        return oldItem.idPelicula == newItem.idPelicula
    }

    override fun areContentsTheSame(oldItem: PeliculaClase, newItem: PeliculaClase): Boolean {
        return oldItem.equals(newItem)
    }
}