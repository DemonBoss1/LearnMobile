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
import java.util.Objects;

public class DataBase {
    private static DataBase dataBase;
    private FirebaseDatabase firebaseDatabase;
    private final FirebaseAuth auth;
    private DatabaseReference databaseReferenceTask;
    private DatabaseReference databaseReferenceGroup;
    private final String TASK_KEY = "Task";
    private final String GROUP_KEY = "Group";
    private DataBase(){
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://dolist-f1f90-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReferenceTask= firebaseDatabase.getReference(TASK_KEY);
        databaseReferenceGroup= firebaseDatabase.getReference(GROUP_KEY);
        getDataFromDB();
    }
    public static void updateFirebaseDatabase(){
        dataBase.firebaseDatabase = FirebaseDatabase.getInstance("https://dolist-f1f90-default-rtdb.europe-west1.firebasedatabase.app");
        dataBase.databaseReferenceTask= dataBase.firebaseDatabase.getReference(dataBase.TASK_KEY);
        dataBase.databaseReferenceGroup= dataBase.firebaseDatabase.getReference(dataBase.GROUP_KEY);
        dataBase.getDataFromDB();
    }
    public static DatabaseReference getRef(String str){
        if(Objects.equals(str, dataBase.TASK_KEY))
            return getDataBase().databaseReferenceTask;
        if(Objects.equals(str, dataBase.GROUP_KEY))
            return getDataBase().databaseReferenceGroup;
        return null;
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
                ArrayList<Task> taskList = ListsForAdapter.getTasks();
                ArrayList<Group> activeGroup = SavedData.getActiveGroups();
                if(activeGroup == null) activeGroup = new ArrayList<>();
                if(taskList.size()>0)taskList.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Task task = ds.getValue(Task.class);
                    for(Group group : activeGroup) {
                        if(task != null && task.idGroup.equals(group.id))
                            ListsForAdapter.getTasks().add(task);
                    }
                }
                TaskListFragment.adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReferenceTask.addValueEventListener(valueEventListener);
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Group> groups = ListsForAdapter.getGroups();
                if(groups.size()>0)groups.clear();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Group group = ds.getValue(Group.class);
                    ListsForAdapter.getGroups().add(group);
                }
                GroupAdapter.updateAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReferenceGroup.addValueEventListener(valueEventListener);
    }
    public static FirebaseUser checkUser(){
        getDataBase();
        FirebaseUser user = dataBase.auth.getCurrentUser();
        Log.v("user", user != null ? user.toString() : "null");
        if(user != null) DataBase.updateFirebaseDatabase();
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
                            RegistrationActivity.loginComplete();
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
