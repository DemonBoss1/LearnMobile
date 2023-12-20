package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.child("id").exists()){
                    Task task = snapshot.getValue(Task.class);
                    TaskList.getTaskList().add(task);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addChildEventListener(listener);
    }
}
