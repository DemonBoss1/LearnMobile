package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {
    Context context;
    ArrayList<Task> taskList;
    LayoutInflater inflater;
    public TaskAdapter(Context context){
        this.context = context;
        this.taskList = ListsForAdapter.getTasks();
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
        TextView text = view.findViewById(R.id.name);
        Button button = view.findViewById(R.id.done_button);
        text.setText(taskList.get(i).text);
        int color = taskList.get(i).importance;
        switch (color){
            case 0:
                text.setTextColor(Color.GRAY);
                break;
            case 1:
                text.setTextColor(Color.RED);
                break;
            default:
                text.setTextColor(Color.GREEN);
        }
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("mes", ""+i);
                Intent intent = new Intent(DoListActivity.activity,TaskActivity.class);
                intent.putExtra("index",i);
                DoListActivity.activity.startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference reference = DataBase.getRef();
                reference.child(ListsForAdapter.getTask(i).id).removeValue();
                ListsForAdapter.removeTask(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}