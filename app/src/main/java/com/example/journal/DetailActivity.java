package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get correct clicked entry and show to user
        Intent intent = getIntent();
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("passed_entry");
        TextView title = findViewById(R.id.detailTitle);
        title.setText(entry.getTitle());
        TextView content = findViewById(R.id.detailText);
        content.setText(entry.getContent());
        Log.d("CONTENT1243", entry.getContent());
        TextView mood = findViewById(R.id.detailMood);
        mood.setText(entry.getMood());
        TextView timestamp = findViewById(R.id.detailStamp);
        timestamp.setText(entry.getTimestamp());
    }

    public void deltailFloatingClicked(View view) {
        // sends user off to create a new journal entry
        Intent intent = new Intent(DetailActivity.this, InputActivity.class);
        startActivity(intent);
    }
}
