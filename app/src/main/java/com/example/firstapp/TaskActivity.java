package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class TaskActivity extends AppCompatActivity {
    TextView title_text;
    EditText description_text;
    Intent intent;
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
        intent = getIntent();
        int i = intent.getIntExtra("index", -1);
        if(i<0) Log.e("error", "i<0");
        Task task = TaskList.getTask(i);
        title_text.setText(task.text);
        description_text.setText(task.description);
    }

    public void backActivity(View view) {
        finish();
    }
    public void deleteTask(View view) {
        int i = intent.getIntExtra("index", -1);
        DatabaseReference reference = DataBase.getRef();
        reference.child(TaskList.getTask(i).id).removeValue();
        TaskList.removeTask(i);
        finish();
    }

    public void editDescription(View view) {
        description_text.setCursorVisible(true);
        description_text.setFocusable(true);
        description_text.setFocusableInTouchMode(true);
        findViewById(R.id.backButton).setVisibility(View.GONE);
        findViewById(R.id.done_button).setVisibility(View.GONE);
        findViewById(R.id.edit_button).setVisibility(View.GONE);
        findViewById(R.id.ok_button).setVisibility(View.VISIBLE);
    }

    public void endEdit(View view) {
        int i = intent.getIntExtra("index", -1);
        DatabaseReference reference = DataBase.getRef();
        reference.child(TaskList.getTask(i).id)
                .setValue(new Task(TaskList.getTask(i).id, title_text.getText().toString(),
                        description_text.getText().toString(), TaskList.getTask(i).importance));
        description_text.setCursorVisible(false);
        description_text.setFocusable(false);
        description_text.setFocusableInTouchMode(false);
        findViewById(R.id.backButton).setVisibility(View.VISIBLE);
        findViewById(R.id.done_button).setVisibility(View.VISIBLE);
        findViewById(R.id.edit_button).setVisibility(View.VISIBLE);
        findViewById(R.id.ok_button).setVisibility(View.GONE);

    }
}