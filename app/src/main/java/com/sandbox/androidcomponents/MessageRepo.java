package com.sandbox.androidcomponents;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.sandbox.androidcomponents.data.AppDatabase;
import com.sandbox.androidcomponents.data.model.TestMessage;

import java.util.List;

/**
 * Repository for messages
 */

public class MessageRepo {
    private static MessageRepo instance;

    private final AppDatabase database;
    private MediatorLiveData<List<TestMessage>> messageList;

    private MessageRepo(final AppDatabase database){
        this.database = database;
        messageList = new MediatorLiveData<>();

        messageList.addSource(database.testMessageDao().loadAllTestMessages(), new Observer<List<TestMessage>>() {
            @Override
            public void onChanged(@Nullable List<TestMessage> s) {
                if(database.getDatabaseCreated().getValue() != null){
                    messageList.setValue(s);
                }
            }
        });
    }

    public static MessageRepo getInstance(final AppDatabase database){
        if(instance == null){
            synchronized (MessageRepo.class){
                if(instance == null){
                    instance = new MessageRepo(database);
                }
            }
        }
        return instance;
    }

    public MutableLiveData<List<TestMessage>> getMessageList() {
        return messageList;
    }

    public void addMessage(TestMessage message){
        database.addMessage(message);
       // this.messageList.add(message);
    }
}
