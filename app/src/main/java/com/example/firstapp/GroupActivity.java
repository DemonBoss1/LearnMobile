package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class GroupActivity extends AppCompatActivity {
    EditText nameGroup;
    ListView groupList;
    public static GroupAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        nameGroup = findViewById(R.id.editTextNameGroup);
        groupList = findViewById(R.id.groupList);
        adapter = new GroupAdapter(this);
        groupList.setAdapter(adapter);
    }

    public void addGroup(View view) {
        ListsForAdapter.addGroup(nameGroup.getText().toString());
        adapter.notifyDataSetChanged();
    }
}