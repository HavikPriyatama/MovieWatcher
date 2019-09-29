package com.moviewatcher.mvw.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VideoMovie implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("id")
    String id;
    @SerializedName("key")
    String key;
    @SerializedName("name")
    String name;
    @SerializedName("site")
    String site;
    @SerializedName("type")
    String type;
    @SerializedName("size")
    int size;

    public VideoMovie(String id, String key, String name, String site, String type, int size) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
        this.type = type;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
