package com.example.firstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskFragment extends Fragment {

    EditText textTask;
    Button buttonAddTask;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task, container, false);
        textTask = v.findViewById(R.id.text_task);
        buttonAddTask = v.findViewById(R.id.button_add_task);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = textTask.getText().toString();
                if(textTask.length()!=0) TaskList.addTask(str);
                textTask.setText("");
            }
        });
        return v;
    }
}