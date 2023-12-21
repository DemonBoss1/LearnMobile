package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class AddTaskFragment extends Fragment {

    EditText textTask;
    EditText textDescription;
    Spinner spinner;
    Button buttonAddTask;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task, container, false);
        textTask = v.findViewById(R.id.text_task);
        textDescription = v.findViewById(R.id.text_description);
        spinner = v.findViewById(R.id.spinner);
        buttonAddTask = v.findViewById(R.id.button_add_task);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = textTask.getText().toString();
                String description = textDescription.getText().toString();
                int b = (int)spinner.getSelectedItemId();
                if(textTask.length()!=0) TaskList.addTask(task, description, b);
                textTask.setText("");
                textDescription.setText("");
            }
        });
        return v;
    }
}