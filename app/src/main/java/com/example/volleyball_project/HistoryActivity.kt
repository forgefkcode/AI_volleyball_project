package com.example.volleyball_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val items = listOf(
            HistoryItem(R.drawable.sample_image_1, "Description 1"),
            HistoryItem(R.drawable.sample_image_2, "Description 2"),
            HistoryItem(R.drawable.sample_image_3, "Description 3"),
            // Add more items as needed
        )

        val adapter = HistoryAdapter(items)
        recyclerView.adapter = adapter
    }
}
