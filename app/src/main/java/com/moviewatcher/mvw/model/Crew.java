package com.moviewatcher.mvw.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Crew implements Serializable {
    private static final long serialVersionUID = 1L;
    @SerializedName("id")
    int id;
    @SerializedName("job")
    String job;
    @SerializedName("name")
    String name;

    public Crew(int id, String job, String name) {
        this.id = id;
        this.job = job;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
