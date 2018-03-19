package com.sandbox.androidcomponents.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.sandbox.androidcomponents.MessageRepo;
import com.sandbox.androidcomponents.data.model.TestMessage;

/**
 * Prepares data for UI
 */

public class MessageViewModel extends ViewModel {
    private final LiveData<TestMessage> testMessage;
    public ObservableField<TestMessage> message = new ObservableField<>();
    private final int messageId;

    public MessageViewModel(MessageRepo messageRepo, final int messageId){
        this.messageId = messageId;
        this.testMessage = messageRepo.loadMessage(messageId);
    }

    public LiveData<TestMessage> getTestMessage(){
        return testMessage;
    }

    public void setMessage(TestMessage testMessage){
        this.message.set(testMessage);
    }
}
