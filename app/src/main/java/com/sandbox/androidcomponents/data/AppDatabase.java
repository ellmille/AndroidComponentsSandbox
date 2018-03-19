package com.sandbox.androidcomponents.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.sandbox.androidcomponents.data.dao.TestMessageDao;
import com.sandbox.androidcomponents.data.model.TestMessage;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Extends database class
 */

@Database(entities = {TestMessage.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "TEST_MESSAGE";
    private static AppDatabase instance;
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    //list DAOs
    public abstract TestMessageDao testMessageDao();

    public static AppDatabase getInstance(final Context context, final Executor executor){
        if(instance == null){
            synchronized (AppDatabase.class){
                if(instance == null){
                    instance = buildDatabase(context.getApplicationContext(), executor);
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context context, final Executor executor){
        return Room.databaseBuilder(context,
                AppDatabase.class, AppDatabase.DB_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        final AppDatabase database = AppDatabase.getInstance(context, executor);
                        //set some fake data
                        final TestMessage messageOne = new TestMessage("Title", "Message");
                        final TestMessage messageTwo = new TestMessage("Another Title", "another message");
                        database.runInTransaction(new Runnable() {
                            @Override
                            public void run() {
                                database.testMessageDao().insertTestMessages(messageOne, messageTwo);
                            }
                        });
                        database.setDatabaseCreated();
                    }
                });
            }
        }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DB_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }

    public void addMessage(final TestMessage testMessage){
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                testMessageDao().insertTestMessage(testMessage);
            }
        });
    }
}
