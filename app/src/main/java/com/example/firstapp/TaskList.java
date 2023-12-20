package com.example.firstapp;

import java.util.ArrayList;

public class TaskList {
    static private ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
    public static void addTask(String str, int b){
        taskList.add(new Task(str, b));
    }
}
