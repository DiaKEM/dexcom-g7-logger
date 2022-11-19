package com.diakem.dexcomg7logger.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { DebugEntity.class }, version = 1)
public abstract class DebugDatabase extends RoomDatabase
{
    public abstract DebugDAO getDebugDao();

    private static DebugDatabase database;

    public static DebugDatabase getInstance(Context context) {
        if (null == database) {
            database = buildDatabaseInstance(context);
        }
        return database;
    }

    private static DebugDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                        DebugDatabase.class,
                        "debugMessages.db")
                .allowMainThreadQueries().build();
    }

    public void cleanUp(){
        database = null;
    }
}
