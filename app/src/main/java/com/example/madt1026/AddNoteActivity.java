package com.example.madt1026;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.madt1026.sourcemanagers.DatabaseManager;
import com.example.madt1026.sourcemanagers.FileManager;
import com.example.madt1026.sourcemanagers.SharedPrefsManager;

public class AddNoteActivity extends AppCompatActivity {

    EditText edNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        this.edNote = findViewById(R.id.edNote);
    }

    public void onBtnSaveAndCloseClick(View view) {
        String noteToAdd = this.edNote.getText().toString();

        SharedPrefsManager.writeNotesToSharedPrefs(this, noteToAdd);
        FileManager.writeStringTofile(this, noteToAdd);
        DatabaseManager.writeNoteToDatabase(this, noteToAdd);
        finish();
    }


}
