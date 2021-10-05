package com.example.notebook_app.activity.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notedb")
data class NoteBook(
    @PrimaryKey(autoGenerate = true)
    val key:Int,
    val title:String,
    val discription:String
):Parcelable
