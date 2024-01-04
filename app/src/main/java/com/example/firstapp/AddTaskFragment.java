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
    Button buttonAddTask;
    Spinner spinner_importance;
    Spinner spinner_group;
    ActiveGroupAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task, container, false);
        init(v);
        setButtons(v);
        setSpinner_group(v);
        return v;
    }
    private void init(View v){
        textTask = v.findViewById(R.id.text_task);
        textDescription = v.findViewById(R.id.text_description);
        spinner_importance = v.findViewById(R.id.spinner_importance);
        spinner_group = v.findViewById(R.id.spinner_group);
        buttonAddTask = v.findViewById(R.id.button_add_task);
    }
    private void setButtons(View v){
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = textTask.getText().toString();
                String description = textDescription.getText().toString();
                int b = (int) spinner_importance.getSelectedItemId();
                int indexGroup = (int) spinner_group.getSelectedItemId();
                String idGroup = SavedData.getActiveGroups().get(indexGroup).id;
                if(textTask.length()!=0) ListsForAdapter.addTask(task, description, b, idGroup);
                textTask.setText("");
                textDescription.setText("");
            }
        });

    }
    private void setSpinner_group(View v){
        adapter = new ActiveGroupAdapter(v.getContext());
        spinner_group.setAdapter(adapter);
    }

}