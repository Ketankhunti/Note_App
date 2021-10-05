package com.example.notebook_app.activity.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notebook_app.activity.model.NoteBook

@Database(entities = [NoteBook::class],version = 1,exportSchema = false)
abstract class NoteBookDatabase:RoomDatabase() {
    abstract fun getNoteBookDao(): NoteBookDao

    companion object {
        @Volatile
        var INSTANCE: NoteBookDatabase? = null

        fun getDatabase(context: Context): NoteBookDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, NoteBookDatabase::class.java, "noteDb").build()

                }
            }
            return INSTANCE
        }
    }
}