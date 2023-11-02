package com.example.firstapp;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    static private List<String> taskList = new ArrayList<>();

    public static List<String> getTaskList() {
        return taskList;
    }
    public static void addTask(String str){
        taskList.add(str);
    }
}
