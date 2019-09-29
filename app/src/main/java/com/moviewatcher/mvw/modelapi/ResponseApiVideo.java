package com.moviewatcher.mvw.modelapi;

import com.google.gson.annotations.SerializedName;
import com.moviewatcher.mvw.model.VideoMovie;

import java.util.List;

public class ResponseApiVideo {
    @SerializedName("results")
    List<VideoMovie> result;

    public ResponseApiVideo(List<VideoMovie> result) {
        this.result = result;
    }

    public List<VideoMovie> getResult() {
        return result;
    }

    public void setResult(List<VideoMovie> result) {
        this.result = result;
    }
}
