package com.example.firstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class SavedData {
    private static ArrayList<Group> activeGroups;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;
    private static final String SHARED_PREFS_FILE = "activeGroups.tl";
    private static final String GROUPS = "groups";


    public static ArrayList<Group> getActiveGroups(Context context) {
        if(prefs == null && editor == null && activeGroups == null) {
            prefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
            editor = prefs.edit();
            try {
                activeGroups = (ArrayList<Group>) ObjectSerializer.deserialize(prefs.getString(GROUPS, ObjectSerializer.serialize(new ArrayList<Group>())));
                if (activeGroups == null) activeGroups = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return activeGroups;
    }
    public static void commitActiveGroups(){
        try {
            if(activeGroups == null) Log.v("commitActiveGroups", "activeGroups is null");
            editor.putString(GROUPS, ObjectSerializer.serialize(activeGroups));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }
}
