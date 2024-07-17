package com.example.volleyball_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class HistoryActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var historyList = listOf("Event 1", "Event 2", "Event 3")  // 示例数据

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listView = findViewById(R.id.listViewHistory)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, historyList)
        listView.adapter = adapter
    }
}
