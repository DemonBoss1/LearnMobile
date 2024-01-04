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

    public static void loadData(Context context){
        prefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        editor = prefs.edit();
        getActiveGroups();
    }
    public static ArrayList<Group> getActiveGroups() {
        if(prefs != null && editor != null && activeGroups == null) {
            try {
                activeGroups = (ArrayList<Group>) ObjectSerializer.deserialize(prefs.getString(GROUPS, ObjectSerializer.serialize(new ArrayList<Group>())));
                if (activeGroups == null) activeGroups = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return activeGroups;
    }
    public static void commitActiveGroups() {
        if (prefs != null && editor != null) {
            try {
                if (activeGroups == null) Log.v("commitActiveGroups", "activeGroups is null");
                editor.putString(GROUPS, ObjectSerializer.serialize(activeGroups));
            } catch (IOException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }
}
