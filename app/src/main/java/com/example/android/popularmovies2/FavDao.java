package com.example.android.popularmovies2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.popularmovies2.Database.FavoriteEntry;

import java.util.List;

@Dao
public interface FavDao {

    @Query("SELECT * FROM task ORDER BY favoriteMovie")
    LiveData<FavoriteEntry> loadAllTasks();

    @Insert
    void insertTask(FavoriteEntry favoriteEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(FavoriteEntry favoriteEntry);

    @Delete
    void deleteTask(FavoriteEntry favoriteEntry);

    @Query("SELECT * FROM task WHERE id = :id")
    LiveData<FavoriteEntry> loadTaskById(int id);
}
