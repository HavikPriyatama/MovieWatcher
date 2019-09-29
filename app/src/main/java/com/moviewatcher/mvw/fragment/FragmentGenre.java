package com.moviewatcher.mvw.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.adapter.ListGenreAdapter;
import com.moviewatcher.mvw.contract.FragmentGenreContract;
import com.moviewatcher.mvw.model.Genre;
import com.moviewatcher.mvw.presenter.FragmentGenrePresenter;
import com.moviewatcher.mvw.utils.IntentManager;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class FragmentGenre extends DaggerFragment implements FragmentGenreContract.View {

    private final static String setTitle = "Genre";
    @BindView(R.id.app_bar)
    Toolbar appBar;
    @BindView(R.id.list_genre)
    RecyclerView listView;
    @Inject
    FragmentGenrePresenter fragmentGenrePresenter;
    @Inject
    Picasso picasso;
    @Inject
    IntentManager intentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_genre, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        fragmentGenrePresenter.getGenre();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_account, menu);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(appBar);
        activity.getSupportActionBar().setTitle(setTitle);
        menu.findItem(R.id.action_appbar).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setGenreAdapter(List<Genre> genres) {
        ListGenreAdapter adapter = new ListGenreAdapter(genres, picasso, intentManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.addItemDecoration(new DividerItemDecoration(listView.getContext(), layoutManager.getOrientation()));
        listView.setAdapter(adapter);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        fragmentGenrePresenter.onDestroy();
        super.onDestroy();
    }
}
