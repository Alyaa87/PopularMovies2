package com.example.android.popularmovies2.Database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

public class AddTaskViewModel extends ViewModel {
    private LiveData<FavoriteEntry> task;

    public AddTaskViewModel(AppDatabase database, int taskId) {
        task = database.mFavDao().loadTaskById(taskId);
    }

    public LiveData<FavoriteEntry> getTask() {
        return task;
    }
}
