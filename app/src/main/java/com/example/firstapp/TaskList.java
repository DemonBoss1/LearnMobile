package com.example.firstapp;

import java.util.ArrayList;

public class TaskList {
    static private ArrayList<String> taskList = new ArrayList<>();

    public static ArrayList<String> getTaskList() {
        return taskList;
    }
    public static void addTask(String str){
        taskList.add(str);
    }
}
