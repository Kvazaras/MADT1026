package com.example.madt1026.sourcemanagers.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes")
public class Note {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "NoteName")
    public String noteName;

    public Note(@NonNull String noteName) {
        this.noteName = noteName;
    }

    @NonNull
    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(@NonNull String noteName) {
        this.noteName = noteName;
    }
}
