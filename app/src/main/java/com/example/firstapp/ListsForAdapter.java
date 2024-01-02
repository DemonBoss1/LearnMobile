package com.example.firstapp;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ListsForAdapter {
    static private final ArrayList<Task> tasks = new ArrayList<>();
    static private final ArrayList<Group> groups = new ArrayList<>();


    public static ArrayList<Task> getTasks() {
        return tasks;
    }
    public static Task getTask(int i) {
        return tasks.get(i);
    }
    public static void removeTask(int i) {
        tasks.remove(i);
    }
    public static void addTask(String text, String description, int b){

        DatabaseReference ref = DataBase.getRef("Task").push();
        Task task = new Task(ref.getKey(), text, description, b);
        ref.setValue(task);
        TaskListFragment.adapter.notifyDataSetChanged();
    }
    public static void removeTaskById(String id) {
        for(Task task: tasks){
            if(task.id.equals(id)){
                tasks.remove(task);
                break;
            }
        }
    }

    public static ArrayList<Group> getGroups(){
        return groups;
    }
    public static void addGroup(String text){
        DatabaseReference ref = DataBase.getRef("Group").push();
        Group group = new Group(ref.getKey(), text);
        ref.setValue(group);
        GroupAdapter.updateAdapter();
    }
    public static void removeGroup(int i) {
        groups.remove(i);
    }
    public static Group getGroup(int i) {
        return groups.get(i);
    }

}
