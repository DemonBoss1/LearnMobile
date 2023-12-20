package com.example.firstapp;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TaskList {
    static private ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
    public static void setTaskList(ArrayList<Task> List) {
        taskList = List;
    }
    public static void addTask(String str, int b){
        DatabaseReference ref = DataBase.getRef().push();
        Task task = new Task(ref.getKey(), str, b);
        ref.setValue(task);
    }
}
