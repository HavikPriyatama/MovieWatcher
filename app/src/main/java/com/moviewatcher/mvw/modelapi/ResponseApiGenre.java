package com.moviewatcher.mvw.modelapi;

import com.google.gson.annotations.SerializedName;
import com.moviewatcher.mvw.model.Genre;
import com.moviewatcher.mvw.model.Movie;

import java.util.List;

public class ResponseApiGenre {
    @SerializedName("genres")
    List<Genre> genres;

    public ResponseApiGenre(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
