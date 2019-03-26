package com.example.journal;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c){
        super(context, layout, c);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // get specific information from cursor
        int id = cursor.getInt(0);
        String title = cursor.getString(1);
        String mood = cursor.getString(3);
        String timestamp = cursor.getString(4);

        // set title
        TextView titleEntry = view.findViewById(R.id.journalTitle);
        titleEntry.setText(title);

        // set image
        ImageView imageEntry = view.findViewById(R.id.imageView);
        // decide what mood image should be shown based on mood
        // all icons are made by: https://www.flaticon.com/authors/roundicons"
        try {
            switch (mood) {
                case "laugh":
                    imageEntry.setImageResource(R.drawable.laugh);
                    break;
                case "happy":
                    imageEntry.setImageResource(R.drawable.thumb_up);
                    break;
                case "sad":
                    imageEntry.setImageResource(R.drawable.anxious);
                    break;
                case "cry":
                    imageEntry.setImageResource(R.drawable.crying);
                    break;
            }
        }
        // set default in case something went wrong with the mood string
        catch (NullPointerException npe){
            imageEntry.setImageResource(R.drawable.laugh);
        }

        // set timestamp
        TextView timestampEntry = view.findViewById(R.id.timestamp);
        timestampEntry.setText(timestamp);

        // set mood text
        TextView moodEntry = view.findViewById(R.id.journalMood);
        moodEntry.setText("Mood for this entry: " + mood);
    }

}
