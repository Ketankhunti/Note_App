package com.example.notebook_app.activity.database

import androidx.room.*
import com.example.notebook_app.activity.model.NoteBook

@Dao
interface NoteBookDao {
    @Insert
    suspend fun insertNote(noteBook: NoteBook)

    @Delete
    suspend fun deleteNote(noteBook: NoteBook)

    @Update
    suspend fun updateNote(noteBook: NoteBook)

    @Query("select * from notedb")
    suspend fun getAllNotes():List<NoteBook>
}