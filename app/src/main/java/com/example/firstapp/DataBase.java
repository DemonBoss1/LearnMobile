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
    private final FirebaseDatabase firebaseDatabase;
    private final FirebaseAuth auth;
    private final DatabaseReference databaseReference;
    private final String TASK_KEY = "Task";
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
        return dataBase.auth.getCurrentUser();
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
        //sentEmailVerification();
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
    public static void sentEmailVerification(){
        getDataBase();
        FirebaseUser user = dataBase.auth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                if(task.isSuccessful()){
                    Log.v("Email", "Complete");
                }else{
                    Log.v("Email", "Not complete");
                }
            }
        });
    }

}
