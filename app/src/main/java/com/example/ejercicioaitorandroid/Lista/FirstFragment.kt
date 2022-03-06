package com.example.ejercicioaitorandroid.Lista

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ejercicioaitorandroid.Database.PeliculaClase
import com.example.ejercicioaitorandroid.R
import com.example.ejercicioaitorandroid.databinding.FragmentFirstBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), OnclickListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //viewmodels
    private lateinit var listaVM:ListaVM

    //recyclerview
    private lateinit var _adapter:Adapter
    private lateinit var _gridlayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar?.title = getString(R.string.lista)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setViewModel()
        _binding?.fab?.visibility = View.VISIBLE

        binding?.fab?.setOnClickListener {
            view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            binding.fab.visibility = View.GONE
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.papelera ->{
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("¿ESTAS SEGURO DE BORRAR?")
                    .setPositiveButton("BORRAR",
                        { dialogInterface, i -> listaVM.borrado() })
                    .setNegativeButton("CANCELAR", null).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setViewModel() {
        listaVM = ViewModelProvider(this)
            .get(ListaVM::class.java)
        listaVM.obtenerTodos().observe(viewLifecycleOwner, { movies->
            _adapter.submitList(movies)
        })
    }
    private fun setRecyclerView() {
        _adapter = Adapter(this)
        _gridlayout = GridLayoutManager(getContext(), 1)

        _binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = _gridlayout
            adapter = _adapter
        }

    }
    override fun onPrepareOptionsMenu(menu: Menu){
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.papelera)
        item.isVisible = true
    }

    override fun onDelete(pelicula: PeliculaClase) {
        listaVM.eliminarpelicula(pelicula)
        Toast.makeText(requireContext(), "Deleting" +pelicula.title, Toast.LENGTH_SHORT).show()
    }
}