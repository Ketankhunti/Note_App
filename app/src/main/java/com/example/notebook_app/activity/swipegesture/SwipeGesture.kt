package com.example.notebook_app.activity.swipegesture

import android.accessibilityservice.GestureDescription
import android.content.Context
import android.graphics.Canvas
import android.media.browse.MediaBrowser
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView




abstract class SwipeGesture(private val context: Context) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

}