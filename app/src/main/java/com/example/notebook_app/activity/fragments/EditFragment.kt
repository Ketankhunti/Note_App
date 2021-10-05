package com.example.notebook_app.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notebook_app.R
import com.example.notebook_app.activity.model.NoteBook
import com.example.notebook_app.activity.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_edit.view.*


class EditFragment : Fragment() {

    val args : EditFragmentArgs by navArgs()
lateinit var mainViewModel: MainViewModel
lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
       mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        view.title_edit.setText( args.currentNote.title)
        view.discription_edit.setText(args.currentNote.discription)

        view.save_instance.setOnClickListener {
            UpdateNote()
        }
        return view
    }

    private fun UpdateNote() {
        navController= view?.let { Navigation.findNavController(it) }!!
       val title = title_edit.text.toString()
        val discription = discription_edit.text.toString()
         val noteBook=NoteBook(args.currentNote.key,title,discription)
        mainViewModel.updateNote(requireContext(),noteBook)
       val action = EditFragmentDirections.actionEditFragmentToMainFragment()
        navController.navigate(action)

        Toast.makeText(requireContext(),"Data has been updated successfully",Toast.LENGTH_SHORT).show()

    }


}