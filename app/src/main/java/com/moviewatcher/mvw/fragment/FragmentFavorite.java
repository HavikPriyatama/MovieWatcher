package com.moviewatcher.mvw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.adapter.FavoriteAdapter;
import com.moviewatcher.mvw.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentFavorite extends Fragment {

    @BindView(R.id.recycleView_favorite)
    RecyclerView recyclerView;
    List<Movie> movies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, rootView);
        FavoriteAdapter adapter = new FavoriteAdapter(movies, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
