package com.example.firstapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    ArrayList<Group> groups;
    private static GroupAdapter groupAdapter;
    public GroupAdapter(){
        this.groups = ListsForAdapter.getGroups();
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
        holder.hint(position);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {
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
                }
            });
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name.setTextColor(Color.GREEN);
                }
            });
        }
        public void hint(int position){
            name.setText(groups.get(position).text);
        }
    }
    public static void updateAdapter(){
        if(groupAdapter != null){
            groupAdapter.notifyDataSetChanged();
        }
    }
}
