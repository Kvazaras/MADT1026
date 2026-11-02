package com.example.madt1026.sourcemanagers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.madt1026.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class SharedPrefsManager {
    public static void writeNotesToSharedPrefs (Context context, String content){
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SHARED_PREF_SOURCE_NOTES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Set<String> savedSet = sharedPref.getStringSet(Constants.SHARED_PREF_SOURCE_NOTES_ARRAY_KEY, null);
        Set<String> newSet = new HashSet<>();
        if(savedSet != null) {
            newSet.addAll(savedSet);
        }
        newSet.add(content);
        editor.putStringSet(Constants.SHARED_PREF_SOURCE_NOTES_ARRAY_KEY, newSet);

        //Adding date
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        editor.putString(Constants.SHARED_PREF_SOURCE_NOTE_KEY_DATE, formattedDate);

        //Adding latest note as sep string
        editor.putString(Constants.SHARED_PREF_SOURCE_NOTE_KEY, content);

        editor.apply();
    }

    public static Set<String> readNotesFromSharedPrefs (Context context){
        //Location of file: /data/data/com.example.madt1026
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.SHARED_PREF_SOURCE_NOTES_FILE, Context.MODE_PRIVATE);

        //String lastSavedNote = sharedPref.getString(Constants.SHARED_PREF_SOURCE_NOTE_KEY, "NA");
        //String lastSavedNoteDate = sharedPref.getString(Constants.SHARED_PREF_SOURCE_NOTE_KEY_DATE, "1900-01-01");
        return sharedPref.getStringSet(Constants.SHARED_PREF_SOURCE_NOTES_ARRAY_KEY, null);
    }
}
