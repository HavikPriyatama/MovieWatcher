package com.moviewatcher.mvw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.activity.LoginActivity;
import com.moviewatcher.mvw.activity.SearchActivity;
import com.moviewatcher.mvw.adapter.PopularAdapter;
import com.moviewatcher.mvw.adapter.TrendingAdapter;
import com.moviewatcher.mvw.contract.FragmentHomeContract;
import com.moviewatcher.mvw.model.Movie;
import com.moviewatcher.mvw.presenter.FragmentHomePresenter;
import com.moviewatcher.mvw.utils.IntentManager;
import com.moviewatcher.mvw.utils.SharedPreferenceManager;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

import static com.moviewatcher.mvw.helper.Constant.FAVORITE;
import static com.moviewatcher.mvw.helper.Constant.IMAGE_MEDIUM_PATH;
import static com.moviewatcher.mvw.helper.Constant.IMAGE_SMALL_PATH;
import static com.moviewatcher.mvw.helper.Constant.VIEW_ALL;


public class FragmentHome extends DaggerFragment implements FragmentHomeContract.View {

    private static final String TAG = "FragmentHome";
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.imageSlider_trending)
    SliderView sliderView;
    @BindView(R.id.title_header)
    TextView titleHeader;
    @BindView(R.id.link_text)
    TextView linkText;
    @BindView(R.id.image_header)
    ImageView imageHeader;
    @BindView(R.id.image_child1)
    ImageView imageChild1;
    @BindView(R.id.image_child2)
    ImageView imageChild2;
    @BindView(R.id.image_child3)
    ImageView imageChild3;
    @BindView(R.id.recycleView_popular)
    RecyclerView recyclerViewPopular;
    @BindView(R.id.progress_popular)
    ProgressBar progressBar;
    @BindView(R.id.app_bar)
    Toolbar appBar;
    @BindView(R.id.toolbar_search)
    EditText searchBar;
    @Inject
    FragmentHomePresenter fragmentHomePresenter;
    @Inject
    Picasso picasso;
    @Inject
    IntentManager intentManager;
    @Inject
    SharedPreferenceManager sharedPreferenceManager;
    private int page = 1;
    private PopularAdapter popularAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        fragmentHomePresenter.getTrending();
        fragmentHomePresenter.getTopRated();
        fragmentHomePresenter.getPopular(page);
        ((AppCompatActivity)getActivity()).setSupportActionBar(appBar);
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(v.getChildAt(v.getChildCount()-1) != null){
                if ((scrollY == (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) && scrollY > oldScrollY){
                    fragmentHomePresenter.loadMorePopular(page);
                }

            }
        });
        searchBar.setOnClickListener(v -> {
            intentManager.noHistoryIntent(SearchActivity.class);
        });
        linkText.setOnClickListener(__ -> {
            intentManager.getListMovie(0, "","Popular Movies", VIEW_ALL);
        });
        return rootView;
    }


    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayTrendingMovie(List<Movie> movies) {
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(6);
        sliderView.startAutoCycle();
        TrendingAdapter sliderViewAdapter = new TrendingAdapter(getActivity().getApplicationContext(), movies, picasso);
        sliderView.setSliderAdapter(sliderViewAdapter);
    }

    @Override
    public void displayTopRatedMovie(List<Movie> movies) {
        titleHeader.setText(R.string.topRated);
        picasso.load(IMAGE_MEDIUM_PATH + movies.get(0).getBackdrop_path()).into(imageHeader);
        imageHeader.setOnClickListener(v -> intentManager.getDetailMovie(movies.get(0).getId()));
        picasso.load(IMAGE_SMALL_PATH + movies.get(1).getPoster_path()).into(imageChild1);
        imageChild1.setOnClickListener(v -> intentManager.getDetailMovie(movies.get(0).getId()));
        picasso.load(IMAGE_SMALL_PATH + movies.get(2).getPoster_path()).into(imageChild2);
        imageChild2.setOnClickListener(v -> intentManager.getDetailMovie(movies.get(0).getId()));
        picasso.load(IMAGE_SMALL_PATH + movies.get(3).getPoster_path()).into(imageChild3);
        imageChild3.setOnClickListener(v -> intentManager.getDetailMovie(movies.get(0).getId()));
    }

    @Override
    public void displayPopularMovie(List<Movie> movies) {
        popularAdapter = new PopularAdapter(movies,getContext(), picasso);
        GridLayoutManager layout = new GridLayoutManager(getContext(), 3);
        recyclerViewPopular.setLayoutManager(layout);
        recyclerViewPopular.setAdapter(popularAdapter);
        page++;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_account, menu);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(appBar);
        menu.findItem(R.id.action_appbar).setIcon(R.drawable.ic_favorite_transparent_24dp);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_appbar && sharedPreferenceManager.getIsLogin()){
            intentManager.getListMovie(0, "","Favorite", FAVORITE);
        }else{
            intentManager.noHistoryIntent(LoginActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadMorePopular(List<Movie> movies) {
        popularAdapter.addPopular(movies);
        page++;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        fragmentHomePresenter.onDestroy();
        super.onDestroyView();
    }
}
