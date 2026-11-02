package com.example.madt1026;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madt1026.sourcemanagers.DatabaseManager;
import com.example.madt1026.sourcemanagers.FileManager;
import com.example.madt1026.sourcemanagers.SharedPrefsManager;
import com.example.madt1026.sourcemanagers.room.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listNoteItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView lvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvNotes = findViewById(R.id.lvNotes);
        this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.listNoteItems);
        this.lvNotes.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Set<String> savedSet = SharedPrefsManager.readNotesFromSharedPrefs(this);
        if (savedSet != null) {
            this.listNoteItems.clear();
            this.listNoteItems.addAll(savedSet);
            this.adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notes_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note:
                Intent i = new Intent(this, AddNoteActivity.class);
                startActivity(i);
                return true;
            case R.id.remove_note:
                Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_LONG).show();
                return true;
            case R.id.read_from_db:
                List<Note> notesFromDb = DatabaseManager.readNotesFromDatabase(this);
                if(!notesFromDb.isEmpty()) {
                    Toast.makeText(getApplicationContext(), notesFromDb.get(0).noteName, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.read_from_file:
                String noteFromFile = FileManager.readStringFromFile(this);
                Toast.makeText(getApplicationContext(), noteFromFile, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

