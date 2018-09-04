package com.example.android.popularmovies2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.popularmovies2.Data.Contract;
import com.squareup.picasso.Picasso;

import static com.example.android.popularmovies2.Data.Contract.EXTRA_OVERVIEW;
import static com.example.android.popularmovies2.Data.Contract.EXTRA_RATE;
import static com.example.android.popularmovies2.Data.Contract.EXTRA_TITLE;
import static com.example.android.popularmovies2.Data.Contract.EXTRA_URL;
import static com.example.android.popularmovies2.Data.Contract.EXTRA_YEAR;

public class DetailMovieActivity extends AppCompatActivity {
    //field to store the movie details
    private String mUrl;
    private String mTitle;
    public TextView mTitleTxt, mReleaseDate, mVoteAverage, mOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie2);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Reference
        mTitleTxt = findViewById(R.id.original_title_tv);
        mReleaseDate = findViewById(R.id.release_date);
        mVoteAverage = findViewById(R.id.vote_average);
        mOverview = findViewById(R.id.overview);
        ImageView mMoviePoster = findViewById(R.id.movie_poster);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        Intent intentStartDetailActivity = getIntent();
        //get the intent
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString(EXTRA_TITLE) != null) {
            mTitle = bundle.getString(EXTRA_TITLE);
            mTitleTxt.setText(mTitle);
        }
        if (bundle.getString(EXTRA_URL) != null) {
            mUrl = bundle.getString(EXTRA_URL);
        }
        if (bundle.getString(EXTRA_YEAR) != null) {
            mReleaseDate.setText(bundle.getString(EXTRA_YEAR));
        }
        if (bundle.getString(EXTRA_RATE) != null) {
            int number = Integer.parseInt(bundle.getString(EXTRA_RATE));
            float d = (float) ((number * 5) / 10);
            ratingBar.setRating(d);
        }
        if (bundle.getString(EXTRA_OVERVIEW) != null) {
            mOverview.setText(bundle.getString(EXTRA_OVERVIEW));
        }
        Picasso.with(this)
                .load(Contract.IMAGE_URL + Contract.W780 + mUrl)
                .into(mMoviePoster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.main, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        if (id == R.id.favorites){
            //open the favorites
            Intent favoritesIntent = new Intent(this,FavoritesActivity.class);
            startActivity(favoritesIntent);
             return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

