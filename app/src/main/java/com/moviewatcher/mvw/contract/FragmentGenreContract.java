package com.moviewatcher.mvw.contract;

import com.moviewatcher.mvw.model.Genre;

import java.util.List;

public class FragmentGenreContract {
    public interface View {
        void setGenreAdapter(List<Genre> genres);
        void onError(String message);
    }

    public interface Presenter{
        void getGenre();
        void onDestroy();
    }
}
