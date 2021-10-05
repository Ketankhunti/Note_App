package com.example.notebook_app.activity.repository

import android.content.Context
import com.example.notebook_app.activity.database.NoteBookDatabase
import com.example.notebook_app.activity.model.NoteBook

class Repository {
    suspend fun insertNote(context: Context, noteBook: NoteBook){
        NoteBookDatabase.getDatabase(context)?.getNoteBookDao()?.insertNote(noteBook)
    }

    suspend fun deleteNote(context: Context, noteBook: NoteBook){
        NoteBookDatabase.getDatabase(context)?.getNoteBookDao()?.deleteNote(noteBook)
    }

    suspend fun getAllNotes(context: Context): List<NoteBook>? {
        return NoteBookDatabase.getDatabase(context)?.getNoteBookDao()?.getAllNotes()
    }

    suspend fun updateNote(context: Context, noteBook: NoteBook){
        NoteBookDatabase.getDatabase(context)?.getNoteBookDao()?.updateNote(noteBook)
    }
}