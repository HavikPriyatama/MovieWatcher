package com.moviewatcher.mvw.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Movie")
public class MovieEntity {
    @PrimaryKey
    @ColumnInfo (name = "MOVIE_ID")
    public int id;

    @ColumnInfo (name = "RATING")
    String vote_average;

    @ColumnInfo (name = "TITLE")
    String tittle;

    @ColumnInfo (name = "PATH_POSTER")
    String poster_path;

    @ColumnInfo (name = "OVERVIEW")
    String overview;

    @ColumnInfo (name = "RELEASE_DATE")
    String release_date;

    @ColumnInfo (name = "PATH_BACKDROP")
    String backdrop_path;

    @ColumnInfo (name = "RUNTIME")
    int runtime;

    @ColumnInfo (name = "ORIGINAL_LANGUAGE")
    String original_language;

    public MovieEntity(int id, String vote_average, String tittle, String poster_path, String overview, String release_date, String backdrop_path, int runtime, String original_language) {
        this.id = id;
        this.vote_average = vote_average;
        this.tittle = tittle;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
        this.runtime = runtime;
        this.original_language = original_language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
}
