package com.example.firstapp;

public class Task {
    public String id;
    public String text;
    public int importance;

    public Task(){

    }
    public Task(String id, String text, int importance){
        this.id = id;
        this.text = text;
        this.importance = importance;
    }
}
