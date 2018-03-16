package com.sandbox.androidcomponents.data.model;

/**
 * Test Class
 */

public class TestMessage {
    private String title, message;
    private long timestamp;

    private TestMessage(){
        this.timestamp = System.currentTimeMillis();
    }
    public TestMessage(String message){
        this();
        this.message = message;
    }
    public TestMessage(String title, String message){
        this(message);
        this.title = title;
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
