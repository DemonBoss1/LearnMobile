package com.example.firstapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataBase {
    private static DataBase dataBase;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private String TASK_KEY = "Task";
    private DataBase(){
        firebaseDatabase = FirebaseDatabase.getInstance("https://dolist-f1f90-default-rtdb.europe-west1.firebasedatabase.app");
        auth = FirebaseAuth.getInstance();
        databaseReference= firebaseDatabase.getReference(TASK_KEY);
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
                    TaskList.getTaskList().add(task);
                }
                //TaskListFragment.adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addValueEventListener(valueEventListener);
    }
    public static FirebaseUser checkUser(){
        getDataBase();
        FirebaseUser user = dataBase.auth.getCurrentUser();
        return user;
    }
    public static void Registration(String login, String password){
        getDataBase();
        dataBase.auth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.v("Database", "User SingUp Successful");
                        }else{
                            Log.e("Database", "User SingUp Error");
                        }
                    }
                });
    }
    public static void Login(String login, String password){
        getDataBase();
        dataBase.auth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.v("Database", "User SingIn Successful");
                        }else{
                            Log.e("Database", "User SingIn Error");
                        }
                    }
                });
    }

}
