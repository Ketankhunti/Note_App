package com.example.notebook_app.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notebook_app.R
import kotlinx.android.synthetic.main.item_list.*

class DataShowingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_showing)

        val intent = intent
        val title = intent.getStringExtra("title")
        val discription = intent.getStringExtra("discription")

        title_list_item.text = title
        desc_list_item.text = discription
    }
}