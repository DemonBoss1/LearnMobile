package com.example.firstapp;

public class Task {
    public String id;
    public String text;
    public String description;
    public int importance;

    public Task(){

    }
    public Task(String id, String text, String description, int importance){
        this.id = id;
        this.text = text;
        this.description = description;
        this.importance = importance;
    }
}
