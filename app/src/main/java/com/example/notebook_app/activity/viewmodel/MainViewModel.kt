package com.example.notebook_app.activity.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook_app.activity.model.NoteBook
import com.example.notebook_app.activity.repository.Repository
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    val repository = Repository()

    val noteList = MutableLiveData<List<NoteBook>>()
    fun insertNote(context: Context, noteBook: NoteBook){
        viewModelScope.launch {
            repository.insertNote(context,noteBook)
        }
    }
    fun deleteNote(context: Context, noteBook: NoteBook){
        viewModelScope.launch {
            repository.deleteNote(context,noteBook)
        }
    }
    fun getAllNoteBooks(context: Context){
        viewModelScope.launch {
            noteList.value = repository.getAllNotes(context)
        }
    }
    fun updateNote(context: Context, noteBook: NoteBook){
        viewModelScope.launch {
            repository.updateNote(context,noteBook)
        }
    }
}