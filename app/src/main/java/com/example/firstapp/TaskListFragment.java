package com.example.firstapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TaskListFragment extends Fragment {

    ListView taskList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);
        taskList = v.findViewById(R.id.task_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.task_list_item, R.id.task_text, TaskList.getTaskList());
        taskList.setAdapter(adapter);

        return v;
    }
}