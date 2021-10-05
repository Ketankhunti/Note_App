package com.example.notebook_app.activity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notebook_app.R
import com.example.notebook_app.activity.model.NoteBook
import com.example.notebook_app.activity.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddFragment : Fragment() {

lateinit var navController: NavController
lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController=Navigation.findNavController(view)

        val title = view.findViewById<EditText>(R.id.title_add)
        val discription = view.findViewById<EditText>(R.id.discription_add)
        val button = view.findViewById<FloatingActionButton>(R.id.add_notebook)
        button.setOnClickListener {
            if (title.text.trim().toString().isEmpty()) return@setOnClickListener

            sevInstance(NoteBook(0,title.text.toString(),discription.text.toString()))

            navController.navigate(R.id.action_addFragment_to_mainFragment)
        }
    }

    private fun sevInstance(noteBook: NoteBook) {

        mainViewModel.insertNote(requireContext(),noteBook)
    }
}