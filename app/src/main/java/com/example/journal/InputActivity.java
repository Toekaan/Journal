package com.example.journal;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InputActivity extends AppCompatActivity {

    private String mood = "";

    // define buttons


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }
    // decide mood based on button press
    public void laughClicked(View view) {
        mood = "laugh";

        // give visual feedback as to what button is currently activated
        ImageButton laugh = findViewById(R.id.laughButton);
        ImageButton happy = findViewById(R.id.happyButton);
        ImageButton sad = findViewById(R.id.sadButton);
        ImageButton cry = findViewById(R.id.cryButton);
        laugh.setColorFilter(null);
        happy.setColorFilter(R.color.colorPrimaryDark);
        sad.setColorFilter(R.color.colorPrimaryDark);
        cry.setColorFilter(R.color.colorPrimaryDark);
    }

    public void happyClicked(View view) {
        mood = "happy";

        ImageButton laugh = findViewById(R.id.laughButton);
        ImageButton happy = findViewById(R.id.happyButton);
        ImageButton sad = findViewById(R.id.sadButton);
        ImageButton cry = findViewById(R.id.cryButton);
        laugh.setColorFilter(R.color.colorPrimaryDark);
        happy.setColorFilter(null);
        sad.setColorFilter(R.color.colorPrimaryDark);
        cry.setColorFilter(R.color.colorPrimaryDark);
    }

    public void sadClicked(View view) {
        mood = "sad";

        ImageButton laugh = findViewById(R.id.laughButton);
        ImageButton happy = findViewById(R.id.happyButton);
        ImageButton sad = findViewById(R.id.sadButton);
        ImageButton cry = findViewById(R.id.cryButton);
        laugh.setColorFilter(R.color.colorPrimaryDark);
        happy.setColorFilter(R.color.colorPrimaryDark);
        sad.setColorFilter(null);
        cry.setColorFilter(R.color.colorPrimaryDark);
    }

    public void cryClicked(View view) {
        mood = "cry";

        ImageButton laugh = findViewById(R.id.laughButton);
        ImageButton happy = findViewById(R.id.happyButton);
        ImageButton sad = findViewById(R.id.sadButton);
        ImageButton cry = findViewById(R.id.cryButton);
        laugh.setColorFilter(R.color.colorPrimaryDark);
        happy.setColorFilter(R.color.colorPrimaryDark);
        sad.setColorFilter(R.color.colorPrimaryDark);
        cry.setColorFilter(null);
    }

    public void addEntry(View view) {
        EditText userTitle = findViewById(R.id.inputTitle);
        String title = userTitle.getText().toString();
        EditText userContent = findViewById(R.id.inputText);
        // check if user has filled in any information at all
        if (title.equals("")){
            return;
        }
        else if (mood.equals("")) {
            // do not allow user to continue without mood selection
            return;
        }
        else if (userContent.equals("")){

        }
        String content = userContent.getText().toString();
        JournalEntry entry = new JournalEntry(title, content, mood);
        EntryDatabase ed = EntryDatabase.getInstance(this);
        /*entry.setTitle((String) title.getText());
        entry.setContent((String) content.getText());*/
        ed.insert(entry);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save all important data to be restored later
        TextView content = findViewById(R.id.inputText);
        String savedContent = content.getText().toString();
        outState.putString("savedContent", savedContent);
        TextView title = findViewById(R.id.inputTitle);
        String savedTitle = title.getText().toString();
        outState.putString("savedTitle", savedTitle);
        outState.putString("savedMood", mood);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        // set title and content
        TextView title = findViewById(R.id.inputTitle);
        title.setText(savedInstanceState.getString("savedTitle"));
        TextView content = findViewById(R.id.inputText);
        content.setText(savedInstanceState.getString("savedContent"));

        // set visible indication of selected button
        mood = savedInstanceState.getString("savedMood");

        ImageButton laugh = findViewById(R.id.laughButton);
        ImageButton happy = findViewById(R.id.happyButton);
        ImageButton sad = findViewById(R.id.sadButton);
        ImageButton cry = findViewById(R.id.cryButton);
        switch (mood) {
            case "laugh":
                laugh.setColorFilter(null);
                happy.setColorFilter(R.color.colorPrimaryDark);
                sad.setColorFilter(R.color.colorPrimaryDark);
                cry.setColorFilter(R.color.colorPrimaryDark);
                break;
            case "happy":
                laugh.setColorFilter(R.color.colorPrimaryDark);
                happy.setColorFilter(null);
                sad.setColorFilter(R.color.colorPrimaryDark);
                cry.setColorFilter(R.color.colorPrimaryDark);
                break;
            case "sad":
                laugh.setColorFilter(R.color.colorPrimaryDark);
                happy.setColorFilter(R.color.colorPrimaryDark);
                sad.setColorFilter(null);
                cry.setColorFilter(R.color.colorPrimaryDark);
                break;
            case "cry":
                laugh.setColorFilter(R.color.colorPrimaryDark);
                happy.setColorFilter(R.color.colorPrimaryDark);
                sad.setColorFilter(R.color.colorPrimaryDark);
                cry.setColorFilter(null);
                break;
        }
    }
}
