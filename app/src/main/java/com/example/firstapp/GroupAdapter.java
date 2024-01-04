package com.example.firstapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    ArrayList<Group> groups;
    ArrayList<Group> activeGroups;
    ArrayList<Integer> colorGroups;
    private static GroupAdapter groupAdapter;
    public GroupAdapter(Context context){
        this.groups = ListsForAdapter.getGroups();
        activeGroups = SavedData.getActiveGroups(context);
        colorGroups = new ArrayList<>();
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
        if(colorGroups.size()!=groups.size()&&colorGroups.size()<=position) {
            boolean isActive = false;
            for (Group group : activeGroups) {
                if (Objects.equals(group.id, groups.get(position).id)) {
                    isActive = true;
                    break;
                }
            }
            if (isActive) colorGroups.add(Color.GREEN);
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
//                    name.setTextColor(Color.RED);
                    colorGroups.set(index, Color.RED);
                    for(Group group : activeGroups) Log.v("DO",group.text);
                    activeGroups.removeIf(group -> group.id.equals(groups.get(index).id));
                    for(Group group : activeGroups) Log.v("POSLE",group.text);
                }
            });
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (colorGroups.get(index) != Color.GREEN) {
                        name.setTextColor(Color.GREEN);
                        colorGroups.set(index, Color.GREEN);
                        activeGroups.add(groups.get(index));
                    }
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

}
