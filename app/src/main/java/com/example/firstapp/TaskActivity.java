package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {
    TextView title_text;
    TextView description_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        init();
        setData();
    }
    private void init(){
        title_text = findViewById(R.id.title_text);
        description_text = findViewById(R.id.description_text);
    }
    private void setData(){
        Intent intent = getIntent();
        int i = intent.getIntExtra("index", -1);
        if(i<0) Log.e("error", "i<0");
        Task task = TaskList.getTask(i);
        title_text.setText(task.text);
        description_text.setText(task.description);
    }

    public void backActivity(View view) {
        finish();
    }
}