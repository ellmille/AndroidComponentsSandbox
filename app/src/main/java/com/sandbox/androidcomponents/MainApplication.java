package com.sandbox.androidcomponents;

import android.app.Application;

import com.sandbox.androidcomponents.data.AppDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Application class
 */

public class MainApplication extends Application {
    private Executor diskIo;

    @Override
    public void onCreate(){
        super.onCreate();
        this.diskIo = Executors.newSingleThreadExecutor();
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this, diskIo);
    }

    public MessageRepo getRepository(){
        return MessageRepo.getInstance(getDatabase());
    }

    public Executor getDiskIo() {
        return diskIo;
    }
}
