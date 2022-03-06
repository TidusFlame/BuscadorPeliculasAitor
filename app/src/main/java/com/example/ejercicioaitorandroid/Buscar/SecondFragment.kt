package com.example.ejercicioaitorandroid.Buscar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.ejercicioaitorandroid.R
import com.example.ejercicioaitorandroid.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //viewmmodel
    private lateinit var buscaVM: BuscaVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModel()
        (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(R.string.encontrar)
        binding?.button?.setOnClickListener {
            if (_binding?.Texto?.text?.isEmpty() == true)
                Toast.makeText(
                    requireContext(),
                    "Añade la pelicula que quieras",
                    Toast.LENGTH_SHORT
                ).show()
            else
                buscaVM.searchResults(_binding?.Texto?.text.toString().trim());
            buscaVM.response.observe(
                viewLifecycleOwner, { peliculas ->
                    buscaVM.saveFilm(peliculas.peliculas[0])
                    view.findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
            )


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setViewModel() {
        buscaVM = ViewModelProvider(this)
            .get(BuscaVM::class.java)

    }
}