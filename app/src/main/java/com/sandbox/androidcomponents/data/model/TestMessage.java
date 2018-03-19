package com.sandbox.androidcomponents.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Test Class
 */

@Entity
public class TestMessage {
    @PrimaryKey
    private int id;
    private String title, message;
    private long timestamp;

    @Ignore
    private TestMessage(int id){
        this.id = id;
        this.timestamp = System.currentTimeMillis();
    }

    public TestMessage(int id, String title, String message){
        this(id);
        this.message = message;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
