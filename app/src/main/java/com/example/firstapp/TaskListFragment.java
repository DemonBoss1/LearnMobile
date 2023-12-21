package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class TaskListFragment extends Fragment {

    ListView taskList;
    public static TaskAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);
        taskList = v.findViewById(R.id.task_list);
        adapter = new TaskAdapter(getContext());
        taskList.setAdapter(adapter);

        return v;
    }
}