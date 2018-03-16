package com.sandbox.androidcomponents.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sandbox.androidcomponents.data.MessageHolder;
import com.sandbox.androidcomponents.data.model.TestMessage;

import java.util.List;

/**
 * Prepares data for the UI
 */

public class ListViewModel extends ViewModel {
    private MessageHolder messageHolder;
    private MutableLiveData<List<TestMessage>> messageList;

    public ListViewModel(){
        messageHolder = new MessageHolder();
    }

    public LiveData<List<TestMessage>> getMessageList(){
        if(messageList == null){
            messageList = new MutableLiveData<>();
            loadMessages();
        }
        return messageList;
    }

    private void loadMessages(){
        List<TestMessage> receivedMessages = messageHolder.getMessageList();
        messageList.postValue(receivedMessages);
    }

    public void addMessageToList(TestMessage message){
        messageHolder.addMessage(message);
        messageList.postValue(messageHolder.getMessageList());
    }
}
