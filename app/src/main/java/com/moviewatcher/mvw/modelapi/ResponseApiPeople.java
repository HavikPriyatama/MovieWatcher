package com.moviewatcher.mvw.modelapi;

import com.google.gson.annotations.SerializedName;
import com.moviewatcher.mvw.model.Cast;
import com.moviewatcher.mvw.model.Crew;

import java.util.List;

public class ResponseApiPeople {
    @SerializedName("cast")
    List<Cast> cast;
    @SerializedName("crew")
    List<Crew> crew;

    public ResponseApiPeople(List<Cast> cast, List<Crew> crew) {
        this.cast = cast;
        this.crew = crew;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }
}
