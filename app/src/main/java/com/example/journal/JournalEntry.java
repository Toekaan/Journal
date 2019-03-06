package com.example.journal;

import java.io.Serializable;
import java.sql.Timestamp;

public class JournalEntry implements Serializable{
    private int id;
    private String title;
    private String content;
    private String mood;
    private String timestamp;

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
