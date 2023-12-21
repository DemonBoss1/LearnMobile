package com.example.firstapp;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TaskList {
    static private ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
    public static Task getTask(int i) {
        return taskList.get(i);
    }
    public static void removeTask(int i) {
        taskList.remove(i);
    }
    public static void setTaskList(ArrayList<Task> List) {
        taskList = List;
    }
    public static void addTask(String text, String description, int b){
        DatabaseReference ref = DataBase.getRef().push();
        Task task = new Task(ref.getKey(), text, description, b);
        ref.setValue(task);
    }
}
