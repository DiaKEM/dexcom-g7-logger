package com.diakem.dexcomg7logger.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Fts4
@Entity(tableName = "debugMessages")
public class DebugEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int id;

    @ColumnInfo(name = "message")
    public String message;

    @ColumnInfo(name = "creation")
    public Long creation;
}
