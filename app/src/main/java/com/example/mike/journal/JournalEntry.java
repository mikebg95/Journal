package com.example.mike.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    private long id;
    private String title;
    private String content;
    private String mood;
    private String timestamp;

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    public JournalEntry(int id, String title, String content, String mood, String timestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public long getId() { return id;}
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getMood() {
        return mood;
    }
//    public String getTimestamp() {
//        return timestamp;
//    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
}
