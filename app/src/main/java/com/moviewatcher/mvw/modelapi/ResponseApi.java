package com.moviewatcher.mvw.modelapi;
import com.google.gson.annotations.SerializedName;
import com.moviewatcher.mvw.model.Genre;
import com.moviewatcher.mvw.model.Movie;

import java.util.List;

public class ResponseApi {
    @SerializedName("results")
    List<Movie> result;

    public ResponseApi(List<Movie> result) {
        this.result = result;
    }

    public List<Movie> getResult() {
        return result;
    }

    public void setResult(List<Movie> result) {
        this.result = result;
    }
}


