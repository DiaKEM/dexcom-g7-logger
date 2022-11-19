package com.diakem.dexcomg7logger.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DebugDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMessages(DebugEntity... debugEntities);

    @Delete
    void delete(DebugEntity debugEntity);

    @Query("SELECT rowid,message,creation FROM debugMessages ORDER BY creation DESC LIMIT :limit")
    LiveData<List<DebugEntity>>getAll(int limit);
}
