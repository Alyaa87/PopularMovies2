package com.example.android.popularmovies2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.example.android.popularmovies2.Database.AddTaskViewModel;
import com.example.android.popularmovies2.Database.AddViewModelFactory;
import com.example.android.popularmovies2.Database.AppDatabase;
import com.example.android.popularmovies2.Database.FavoriteEntityy;

public class FavoritesActivity extends AppCompatActivity {
    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    private static final int DEFAULT_TASK_ID = -1;

    CheckBox mCheckBox;
    private int mTaskId = DEFAULT_TASK_ID;

    // Member variable for the Database
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mCheckBox.setText(R.string.action_favorites);
            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);
                AddViewModelFactory factory = new AddViewModelFactory(mDb, mTaskId);

                final AddTaskViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(AddTaskViewModel.class);

                viewModel.getTask().observe(this, new Observer<FavoriteEntityy>() {
                    @Override
                    public void onChanged(@Nullable FavoriteEntityy taskEntry) {
                        viewModel.getTask().removeObserver(this);
                        populateUI(taskEntry);
                    }
                });
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateUI(FavoriteEntityy task) {
        if (task == null) {
            return;
        }
    }
}



