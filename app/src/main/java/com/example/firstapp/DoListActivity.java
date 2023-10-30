package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DoListActivity extends AppCompatActivity {

    ListView taskList;
    String[] strArr = new String[]{"Task1", "Task2", "Task3", "Task4", "Task5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_list);

        taskList = findViewById(R.id.task_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strArr);
        taskList.setAdapter(adapter);
    }
}