package com.moviewatcher.mvw.contract;

import com.moviewatcher.mvw.model.Movie;

import java.util.List;

public class SearchContract {
    public interface View{
        void setSearchList(List<Movie> movies);
        void onError(String message);
    }

    public interface Presenter{
        void onTextChange(String s);
        void onDestroy();
    }
}
