package com.example.firstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    private final String SHARED_PREFS_FILE = "activeGroups.tl";
    private final String GROUPS = "groups";
    ArrayList<Group> groups;
    ArrayList<String> activeGroups;
    ArrayList<Integer> colorGroups;
    private static GroupAdapter groupAdapter;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public GroupAdapter(Context context){
        this.groups = ListsForAdapter.getGroups();
        activeGroups = new ArrayList<>();
        colorGroups = new ArrayList<>();
        prefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        editor = prefs.edit();
        try {
            activeGroups = (ArrayList<String>) ObjectSerializer.deserialize(prefs.getString(GROUPS, ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        groupAdapter = this;
    }
    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_list_item, parent, false);
        GroupViewHolder holder = new GroupViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        if(colorGroups.size()!=groups.size()&&colorGroups.size()<=position){
            boolean isActive = false;
            for(String id : activeGroups){
                if(Objects.equals(id, groups.get(position).id)){
                    isActive = true;
                    break;
                }
            }
            if(isActive) colorGroups.add(Color.GREEN);
            else colorGroups.add(Color.RED);
        }
        holder.bint(position);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {
        int index;
        TextView name;
        Button deleteButton;
        Button addButton;
        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            deleteButton = itemView.findViewById(R.id.delete_button);
            addButton = itemView.findViewById(R.id.add_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name.setTextColor(Color.RED);
                    colorGroups.set(index, Color.RED);
                    activeGroups.removeIf(id -> Objects.equals(id, groups.get(index).id));
                }
            });
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name.setTextColor(Color.GREEN);
                    colorGroups.set(index, Color.GREEN);
                    activeGroups.add(groups.get(index).id);
                }
            });
        }
        public void bint(int position){
            index = position;
            name.setText(groups.get(position).text);
            name.setTextColor(colorGroups.get(position));
        }
    }
    public static void updateAdapter(){
        if(groupAdapter != null){
            groupAdapter.notifyDataSetChanged();
        }
    }
    public void commitActiveGroups(){
        try {
            editor.putString(GROUPS, ObjectSerializer.serialize(activeGroups));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }
}
