package com.sandbox.androidcomponents.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sandbox.androidcomponents.data.model.TestMessage;

/**
 * Data Access Object for Test Messages
 */

@Dao
public interface TestMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTestMessages(TestMessage... testMessages);

    @Insert
    public void insertTestMessage(TestMessage testMessage);

    @Update
    public void updateTestMessage(TestMessage testMessage);

    @Delete
    public void deleteTestMessage(TestMessage testMessage);

    @Query("SELECT * FROM TestMessage")
    public TestMessage[] loadAllTestMessages();
}
