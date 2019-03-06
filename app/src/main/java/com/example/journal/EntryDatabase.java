package com.example.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class EntryDatabase extends SQLiteOpenHelper {

    // private int version;
    private static EntryDatabase instance;

    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new EntryDatabase(context, "Journal", null, 6);
            return instance;
        }
    }

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlquery = "create table entries (_id INTEGER PRIMARY KEY , title TEXT, content TEXT, mood TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(sqlquery);
        String erik = "Erik's dag";
        String content = "Ik was vandaag uiterst bedroefd ;_;";
        String mood = "sad";
        String sqlquery1 = "INSERT INTO entries(title, content, mood) VALUES('erik', 'content', 'sad')";
        db.execSQL(sqlquery1);
        String sqlquery2 = "INSERT INTO entries(title, content, mood) VALUES('bad day', 'no content at all', 'happy')";
        db.execSQL(sqlquery2);
        String testings = "SELECT title FROM entries";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }
    public Cursor selectAll(){
        return getWritableDatabase().rawQuery("SELECT * FROM entries", null);

    }

    public void insert(JournalEntry je){
        getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", je.getTitle());
        cv.put("content", je.getContent());
        cv.put("mood", je.getMood());
        getWritableDatabase().insert("entries", null, cv);

    }
    public void delete(long id){
        /*SQLiteDatabase db = this.getWritableDatabase();
        db.delete("entries", "id = ?", long[]{id});*/
        String query = "DELETE FROM entries WHERE _id = ('"+id+"')";
        getWritableDatabase().execSQL(query);
    }
}
