package com.sandbox.androidcomponents.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.sandbox.androidcomponents.MainApplication;
import com.sandbox.androidcomponents.data.model.TestMessage;

import java.util.List;

/**
 * Prepares data for the UI
 */

public class ListViewModel extends AndroidViewModel {
    private MediatorLiveData<List<TestMessage>> messageList;

    public ListViewModel(Application application){
        super(application);

        messageList = new MediatorLiveData<>();
        messageList.setValue(null);

        LiveData<List<TestMessage>> messages = ((MainApplication) application).getRepository().getMessageList();

        messageList.addSource(messages, new Observer<List<TestMessage>>() {
            @Override
            public void onChanged(@Nullable List<TestMessage> testMessages) {
                messageList.setValue(testMessages);
            }
        });
    }

    public LiveData<List<TestMessage>> getMessageList(){
        return messageList;
    }

//    private void loadMessages(){
//        messageList = messageRepo.getMessageList();
////        List<TestMessage> receivedMessages =  messageHolder.getMessageList();
////        messageList.postValue(receivedMessages);
//    }
//
//    public void addMessageToList(TestMessage message){
//        messageRepo.addMessage(message);
//        messageList = messageRepo.getMessageList();
////        messageHolder.addMessage(message);
////        messageList.postValue(messageHolder.getMessageList());
//    }
}
