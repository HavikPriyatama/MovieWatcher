package com.moviewatcher.mvw.contract;

import com.moviewatcher.mvw.model.Movie;

import java.util.List;

public class ListMovieContract {
    public interface View{
        void onComplete(List<Movie> movies);
        void onError(String message);
        void onLoadMore(List<Movie> newMovies);
        void progressBarOn();
        void progressBarOff();
    }

    public interface Presenter{
        void getList(String viewType,int genreId, int page, String result);
        void getLoadMore(String viewType,int genreId, int page, String result);
        void onDestroy();
    }
}
