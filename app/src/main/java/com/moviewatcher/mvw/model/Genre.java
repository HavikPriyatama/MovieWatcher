package com.moviewatcher.mvw.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    String backdropImage;

    public Genre() {
    }

    public Genre(int id, String name, String backdropImage) {
        this.id = id;
        this.name = name;
        this.backdropImage = backdropImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage(String backdropImage) {
        this.backdropImage = backdropImage;
    }
}
