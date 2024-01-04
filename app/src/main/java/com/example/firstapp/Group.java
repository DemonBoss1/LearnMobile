package com.example.firstapp;

import java.io.Serializable;

public class Group implements Serializable {
    public String id;
    public String text;

    public Group (){

    }
    public Group(String id, String text){
        this.id = id;
        this.text = text;
    }
}
