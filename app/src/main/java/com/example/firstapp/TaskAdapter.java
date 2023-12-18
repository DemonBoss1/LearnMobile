package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> taskList;
    LayoutInflater inflater;
    public TaskAdapter(Context context){
        this.context = context;
        this.taskList = TaskList.getTaskList();
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.task_list_item, null);
        TextView text = view.findViewById(R.id.task_text);
        Button button = view.findViewById(R.id.done_button);
        text.setText(taskList.get(i));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskList.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}