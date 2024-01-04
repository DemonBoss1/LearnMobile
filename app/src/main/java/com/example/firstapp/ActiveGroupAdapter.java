package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ActiveGroupAdapter extends BaseAdapter {
    Context context;
    ArrayList<Group> activeGroups;
    LayoutInflater inflater;
    public ActiveGroupAdapter(Context context){
        this.context = context;
        this.activeGroups = SavedData.getActiveGroups(context);
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return activeGroups.size();
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
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner_dropdown_item, null);
        TextView spinnerDropdown = view.findViewById(R.id.spinnerdropdown);
        spinnerDropdown.setText(activeGroups.get(i).text);
        return view;
    }
}
