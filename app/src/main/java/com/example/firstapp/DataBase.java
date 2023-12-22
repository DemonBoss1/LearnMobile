package com.example.firstapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataBase {
    private static DataBase dataBase;
    private DatabaseReference databaseReference;
    private String TASK_KEY = "Task";
    private DataBase(){
        databaseReference= FirebaseDatabase.getInstance("https://dolist-f1f90-default-rtdb.europe-west1.firebasedatabase.app").getReference(TASK_KEY);
        getDataFromDB();
    }
    public static DatabaseReference getRef(){
        return getDataBase().databaseReference;
    }

    public static DataBase getDataBase() {
        if(dataBase==null) {
            dataBase=new DataBase();
        }
        return dataBase;
    }
    private void getDataFromDB(){
       ArrayList<Task> taskList = new ArrayList<>();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Task> taskList = TaskList.getTaskList();
                if(taskList.size()>0)taskList.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Task task = ds.getValue(Task.class);
                    Log.v("firebase", task.text);
                    TaskList.getTaskList().add(task);
                }
                TaskListFragment.adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}
