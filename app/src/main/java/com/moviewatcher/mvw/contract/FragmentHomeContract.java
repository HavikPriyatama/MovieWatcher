package com.moviewatcher.mvw.contract;

import com.moviewatcher.mvw.model.Movie;

import java.util.List;

public class FragmentHomeContract {
    public interface View{
        void onError(String message);
        void displayTrendingMovie(List<Movie> movies);
        void displayTopRatedMovie(List<Movie> movies);
        void displayPopularMovie(List<Movie> movies);
        void loadMorePopular(List<Movie> movies);
        void showLoading();
        void stopLoading();

    }

    public interface Presenter{
        void getTrending();
        void getTopRated();
        void getPopular(int page);
        void loadMorePopular(int page);
        void onDestroy();

    }
}
