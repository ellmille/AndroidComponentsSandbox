package com.sandbox.androidcomponents.data;

import com.sandbox.androidcomponents.data.model.TestMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds list of messages
 */

public class MessageHolder {
    private List<TestMessage> messageList;

    public MessageHolder(){
        messageList = new ArrayList<TestMessage>();
        messageList.add(new TestMessage("Title", "Message"));
        messageList.add(new TestMessage("just a message"));
    }

    public List<TestMessage> getMessageList(){
        return messageList;
    }

    public void setMessageList(List<TestMessage> messageListIn){
        this.messageList = messageListIn;
    }

    public void addMessage(TestMessage message){
        this.messageList.add(message);
    }
}
