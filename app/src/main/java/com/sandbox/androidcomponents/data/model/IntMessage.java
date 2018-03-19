package com.sandbox.androidcomponents.data.model;

import java.util.Random;

/**
 * POJO to hold ints
 */

public class IntMessage {
    private int summary;
    private int messageOne, messageTwo, messageThree;
    private Random random;

    public IntMessage(){
        this.random = new Random();
        this.messageOne = random.nextInt();
        this.messageTwo = random.nextInt();
        this.messageThree = random.nextInt();
        this.summary = (messageOne + messageTwo + messageThree) / 3;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public int getMessageOne() {
        return messageOne;
    }

    public void setMessageOne(int messageOne) {
        this.messageOne = messageOne;
    }

    public int getMessageTwo() {
        return messageTwo;
    }

    public void setMessageTwo(int messageTwo) {
        this.messageTwo = messageTwo;
    }

    public int getMessageThree() {
        return messageThree;
    }

    public void setMessageThree(int messageThree) {
        this.messageThree = messageThree;
    }

    public void updateMessages(){
        this.messageOne = random.nextInt();
        this.messageTwo = random.nextInt();
        this.messageThree = random.nextInt();
        this.summary = (messageOne + messageTwo + messageThree) / 3;
    }
}
