package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class GroupActivity extends AppCompatActivity {
    EditText nameGroup;
    RecyclerView groupList;
    public GroupAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        nameGroup = findViewById(R.id.editTextNameGroup);
        groupList = findViewById(R.id.groupList);
        groupList.setLayoutManager(new LinearLayoutManager(this){

        });
        adapter = new GroupAdapter(this);
        groupList.setAdapter(adapter);
    }

    public void addGroup(View view) {
        ListsForAdapter.addGroup(nameGroup.getText().toString());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SavedData.commitActiveGroups();
    }
}