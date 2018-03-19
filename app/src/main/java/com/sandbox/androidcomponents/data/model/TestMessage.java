package com.sandbox.androidcomponents.data.model;

/**
 * Test Class
 */

public class TestMessage {
    private int id;
    private String title, message;
    private long timestamp;

    private TestMessage(int id){
        this.id = id;
        this.timestamp = System.currentTimeMillis();
    }
    public TestMessage(int id, String message){
        this(id);
        this.message = message;
    }
    public TestMessage(int id, String title, String message){
        this(id, message);
        this.title = title;
    }

    public int getId() {
        return id;
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
