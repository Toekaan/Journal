package com.example.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //Adapter adapter;
    private EntryDatabase db = EntryDatabase.getInstance(this);

    /*Cursor cursor = db.selectAll();*/
    EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // listview listeners
        ListView list = findViewById(R.id.listView);

        Cursor cursor = db.selectAll();

        // link database and entry_row layout
        int layout = R.layout.entry_row;
        adapter = new EntryAdapter(this, layout, cursor);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // sends user off to clicked entry
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                // convert SQLiteCursor to JournalEntry object so that we can give it via intent
                SQLiteCursor clickedEntry = (SQLiteCursor) parent.getItemAtPosition(position);
                JournalEntry passedEntry = new JournalEntry(clickedEntry.getString(1), clickedEntry.getString(2), clickedEntry.getString(3));
                passedEntry.setId(clickedEntry.getInt(0));
                passedEntry.setTimestamp(clickedEntry.getString(4));
                intent.putExtra("passed_entry", passedEntry);
                startActivity(intent);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SQLiteCursor clickedEntry = (SQLiteCursor) parent.getItemAtPosition(position);
                String name = clickedEntry.getColumnName(0);
                Log.d("Longclicked", name);
                int del = clickedEntry.getInt(0);
                // TO DO.
                db.delete(del);
                updateData();
                return true;
            }
        });
    }

    public void floatingClicked(View view) {
        // sends user off to create a new journal entry
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private void updateData(){
        Cursor cursor = db.selectAll();

        // link database and entry_row layout
        adapter.swapCursor(cursor);
    }

    public void listClicked(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // do something
    }
}
