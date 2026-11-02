package com.example.madt1026.sourcemanagers;

import android.content.Context;

import com.example.madt1026.sourcemanagers.room.AppDatabase;
import com.example.madt1026.sourcemanagers.room.Note;

import java.util.List;

public class DatabaseManager {

    public static void writeNoteToDatabase(Context context, String noteName) {
        AppDatabase db = AppDatabase.getInstance(context);
        Note note = new Note(noteName);
        db.noteDao().insertNote(note);
    }

    public static List<Note> readNotesFromDatabase(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        return db.noteDao().getAllNotes();
    }
}
