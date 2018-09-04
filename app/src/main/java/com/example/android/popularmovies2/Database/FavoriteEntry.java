package com.example.android.popularmovies2.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "task")
public class FavoriteEntry {
    //  Annotate the class with Entity. Use "task" for the table name
        //Annotate the id as PrimaryKey. Set autoGenerate to true.
        @PrimaryKey(autoGenerate = true)
        private int id;
        private String description;
        @ColumnInfo(name = "favorite")
        private Boolean favoriteMovie;

        // use the Ignore annotation so Room knows that it has to use the other constructor instead
        @Ignore
        public FavoriteEntry(String description, int id , Boolean favoriteMovie) {
            this.description = description;
            this.favoriteMovie=favoriteMovie;
            this.id=id;
        }

        public FavoriteEntry(int id, String description, Boolean favoriteMovie) {
            this.id = id;
            this.favoriteMovie=favoriteMovie;
            this.description = description;
        }
//Getter and Setter method
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Boolean getFavoriteMovie() {
            return favoriteMovie;
        }

        public void setFavoriteMovie(Boolean favoriteMovie) {
            this.favoriteMovie = favoriteMovie;
        }
    }

