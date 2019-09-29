package com.moviewatcher.mvw.activity;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.adapter.ListMovieAdapter;
import com.moviewatcher.mvw.contract.ListMovieContract;
import com.moviewatcher.mvw.model.Movie;
import com.moviewatcher.mvw.presenter.ListMoviePresenter;
import com.moviewatcher.mvw.utils.IntentManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static com.moviewatcher.mvw.helper.Constant.APPBAR_TITLE;
import static com.moviewatcher.mvw.helper.Constant.GENRE_ID;
import static com.moviewatcher.mvw.helper.Constant.LIST_TYPE;
import static com.moviewatcher.mvw.helper.Constant.SEARCH_RESULT;

public class ListMovieActivity extends DaggerAppCompatActivity implements ListMovieContract.View {

    private static final String TAG = "ListMovieActivity";
    @BindView(R.id.app_bar)
    Toolbar appBarr;
    @BindView(R.id.list_view_movie)
    ListView movieListView;
    @BindView(R.id.progressBar_list)
    ProgressBar progressBar;
    @Inject
    ListMoviePresenter listMoviePresenter;
    @Inject
    Picasso picasso;
    @Inject
    IntentManager intentManager;
    private int page = 1;
    private int genreId;
    private String searchResult;
    private String viewType;
    private ListMovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        ButterKnife.bind(this);
        setSupportActionBar(appBarr);
        setTitle(getIntent().getStringExtra(APPBAR_TITLE));
        genreId = getIntent().getIntExtra(GENRE_ID, 0);
        searchResult = getIntent().getStringExtra(SEARCH_RESULT);
        viewType = getIntent().getStringExtra(LIST_TYPE);
        listMoviePresenter.getList(viewType, genreId, page, searchResult);
        movieListView.setOnItemClickListener((parent, view, position, id) -> intentManager.getDetailMovie((int) id));
        movieListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                isScrollComplete();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void isScrollComplete(){
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount && this.currentScrollState == SCROLL_STATE_IDLE){
                    listMoviePresenter.getLoadMore(viewType, genreId, page, searchResult);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_account, menu);
        menu.findItem(R.id.action_appbar).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onComplete(List<Movie> movies) {
        adapter =  new ListMovieAdapter(movies,picasso, this);
        movieListView.setAdapter(adapter);
        page++;
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore(List<Movie> newMovies) {
        adapter.addMovies(newMovies);
        page++;
    }

    @Override
    public void progressBarOn() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void progressBarOff() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        listMoviePresenter.onDestroy();
        super.onDestroy();
    }
}
