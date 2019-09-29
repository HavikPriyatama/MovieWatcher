package com.moviewatcher.mvw.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cast implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("cast_id")
    int cast_id;
    @SerializedName("id")
    int id;
    @SerializedName("character")
    String character;
    @SerializedName("name")
    String name;
    @SerializedName("profile_path")
    String profile_path;
    @SerializedName("order")
    int order;

    public Cast(int cast_id, int id, String character, String name, String profile_path, int order) {
        this.cast_id = cast_id;
        this.id = id;
        this.character = character;
        this.name = name;
        this.profile_path = profile_path;
        this.order = order;
    }

    public int getCast_id() {
        return cast_id;
    }

    public void setCast_id(int cast_id) {
        this.cast_id = cast_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
