package com.sandbox.androidcomponents;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.sandbox.androidcomponents.data.model.TestMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for messages
 */

public class MessageRepo {
    private List<TestMessage> messageList;

    public MessageRepo(){
        messageList = new ArrayList<TestMessage>();
        messageList.add(new TestMessage(0, "Title", "Message"));
        messageList.add(new TestMessage(1, "just a message"));
    }

    public LiveData<List<TestMessage>> getMessageList() {
        final MutableLiveData<List<TestMessage>> data = new MutableLiveData<>();
        data.setValue(messageList);
        return data;
    }

    public void addMessage(TestMessage message){
        this.messageList.add(message);
    }
}
