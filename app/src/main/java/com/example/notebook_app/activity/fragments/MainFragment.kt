package com.example.notebook_app.activity.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook_app.R
import com.example.notebook_app.activity.activity.ContainerAcivity
import com.example.notebook_app.activity.adapter.MyAdapter
import com.example.notebook_app.activity.model.NoteBook
import com.example.notebook_app.activity.swipegesture.SwipeGesture
import com.example.notebook_app.activity.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment: Fragment() {

    lateinit var navController: NavController
lateinit var mainViewModel: MainViewModel
lateinit var noteList: MutableList<NoteBook>
    private val adapter = MyAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        val button = view.findViewById<FloatingActionButton>(R.id.add_button)
        button.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_addFragment)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getAllNoteBooks(requireContext())
        mainViewModel.noteList.observe(viewLifecycleOwner, Observer {
            noteList = it as MutableList<NoteBook>
            adapter.setListItems(it)
        })


        val swipegesture = object : SwipeGesture(requireContext()) {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {

                    ItemTouchHelper.RIGHT -> {

                        var position = viewHolder.adapterPosition
                        val key = adapter.notelist[position].key
                        val title = adapter.notelist[position].title
                        val discription = adapter.notelist[position].discription
                        val noteBook = NoteBook(key, title, discription)
                        adapter.deleteNote(noteBook)
                        adapter.notelist.removeAt(viewHolder.adapterPosition)
                        adapter.notifyDataSetChanged()
                        Toast.makeText(
                            requireContext(),
                            "Data has benn deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    ItemTouchHelper.LEFT -> {
                        val position = viewHolder.adapterPosition
                        val key = adapter.notelist[position].key
                        val title = adapter.notelist[position].title
                        val discription = adapter.notelist[position].discription
                        val action = MainFragmentDirections.actionMainFragmentToEditFragment(
                            NoteBook(
                                key,
                                title,
                                discription
                            )
                        )
                        navController.navigate(action)
                    }
                }

            }
        }

        recyclerView.adapter = adapter
        val touchHelper = ItemTouchHelper(swipegesture)
        touchHelper.attachToRecyclerView(recyclerView)
    }
}