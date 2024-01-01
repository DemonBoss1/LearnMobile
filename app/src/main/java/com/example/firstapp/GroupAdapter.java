package com.example.firstapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> groups;
    LayoutInflater inflater;
    TextView name;
    public GroupAdapter(Context context){
        this.context = context;
        this.groups = ListsForAdapter.getGroups();
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return groups.size();
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
        view = inflater.inflate(R.layout.group_list_item, null);
        name = view.findViewById(R.id.name);
        Button deleteButton = view.findViewById(R.id.delete_button);
        Button addButton = view.findViewById(R.id.add_button);
        name.setText(groups.get(i));
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setTextColor(Color.RED);
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setTextColor(Color.GREEN);
            }
        });
        return view;
    }
}
