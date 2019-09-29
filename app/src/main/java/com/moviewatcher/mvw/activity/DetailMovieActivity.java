package com.moviewatcher.mvw.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.adapter.CastAdapter;
import com.moviewatcher.mvw.adapter.CrewAdapter;
import com.moviewatcher.mvw.adapter.SimiliarAdapter;
import com.moviewatcher.mvw.adapter.TrailerAdapter;
import com.moviewatcher.mvw.api.ApiClient;
import com.moviewatcher.mvw.api.ApiInterface;
import com.moviewatcher.mvw.contract.DetailMovieContract;
import com.moviewatcher.mvw.model.Cast;
import com.moviewatcher.mvw.model.Crew;
import com.moviewatcher.mvw.model.Genre;
import com.moviewatcher.mvw.model.Movie;
import com.moviewatcher.mvw.model.VideoMovie;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.moviewatcher.mvw.helper.Constant.IMAGE_MEDIUM_PATH;
import static com.moviewatcher.mvw.helper.Constant.IMAGE_ORIGINAL_PATH;
import static com.moviewatcher.mvw.helper.Constant.IMAGE_SMALL_PATH;
import static com.moviewatcher.mvw.helper.Constant.MOVIE_ID;
import static com.moviewatcher.mvw.helper.Constant.MOVIE_PATH;
import static com.moviewatcher.mvw.helper.Constant.TRAILER;

public class DetailMovieActivity extends AppCompatActivity implements DetailMovieContract.View {

    @BindView(R.id.banner_detail)
    ImageView bannerDetail;
    @BindView(R.id.poster_detail)
    ImageView posterDetail;
    @BindView(R.id.title_detail)
    TextView titleDetail;
    @BindView(R.id.rating_detail)
    TextView ratingDetail;
    @BindView(R.id.favorite_detail)
    TextView favoriteDetail;
    @BindView(R.id.favorite_icon)
    ImageView favoriteIcon;
    @BindView(R.id.overview_detail)
    TextView overviewDetail;
    @BindView(R.id.duration_detail)
    TextView durationDetail;
    @BindView(R.id.leng_detail)
    TextView lengDetail;
    @BindView(R.id.genre_detail)
    TextView genreDetail;
    @BindView(R.id.recycleView_similiar)
    RecyclerView recyclerViewSimiliar;
    @BindView(R.id.recycleView_cast)
    RecyclerView recyclerViewCast;
    @BindView(R.id.recycleView_crew)
    RecyclerView recyclerViewCrew;
    @BindView(R.id.recycleView_trailer)
    RecyclerView recyclerViewTrailer;
    private Movie movie;
    private boolean isFav;
    private List<Movie> movies;
    private List<Cast> castList;
    private  List<Crew> crewList;
    private List<VideoMovie> videoMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        int movieId = getIntent().getIntExtra(MOVIE_ID, 0);
        if (isFav) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_pink_24dp);
        }else{
            favoriteIcon.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        fetchDataDetail(movieId);
        fetchDataSimiliar(movieId);
        fetchDataCast(movieId);
        fetchDataTrailer(movieId);
        favoriteDetail.setOnClickListener(v -> {
            if (isFav){

                favoriteIcon.setImageResource(R.drawable.ic_favorite_black_24dp);
               Toast.makeText(getBaseContext(), "SUCCESS DELETE", Toast.LENGTH_SHORT).show();
                isFav = false;
            }else{

                favoriteIcon.setImageResource(R.drawable.ic_favorite_pink_24dp);
                Toast.makeText(getBaseContext(), "SUCCESS ADD", Toast.LENGTH_LONG).show();
                isFav = true;
            }


        });

    }

    private void fetchDataDetail(int movieId) {
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        apiInterface.getDetailMovie(movieId, ApiInterface.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseApi -> {
                    movie = responseApi;
                    setValueDetail();
                }, throwable -> {
                }, () -> {
                }, disposable -> {
                });
    }


    private void fetchDataSimiliar(int movieId) {
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        apiInterface.getSimilar(movieId, ApiInterface.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseApi -> {
                    movies = responseApi.getResult();
                    setValueSimiliar();
                }, throwable -> {
                }, () -> {
                }, disposable -> {
                });
    }

    private void fetchDataCast(int movieId) {
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        apiInterface.getPeopleMovie(movieId, ApiInterface.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseApiPeople -> {
                    castList = responseApiPeople.getCast();
                    crewList = responseApiPeople.getCrew();
                    if(castList != null){
                        setValueCast();
                    }
                    if(crewList != null){
                        setValueCrew();
                    }
                }, throwable -> {
                }, () -> {
                }, disposable -> {
                });
    }

    private void fetchDataTrailer(int movieId) {
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        apiInterface.getVideoMovie(movieId, ApiInterface.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseApi -> {
                    videoMovies = responseApi.getResult();
                    setValueTrailer();
                }, throwable -> {
                }, () -> {
                }, disposable -> {
                });
    }

    private void setValueDetail() {
        String urlBanner = IMAGE_MEDIUM_PATH + movie.getBackdrop_path();
        String urlPoster = IMAGE_SMALL_PATH + movie.getPoster_path();
        String genre = "";
        String duration = movie.getRuntime() + " mins";
        List<Genre> genres = movie.getGenres();

        for (int i = 0; i < genres.size(); i++) {
            genre = genre + genres.get(i).getName() + "  ";
        }
        Picasso.get().load(urlBanner).into(bannerDetail);
        Picasso.get().load(urlPoster).into(posterDetail);
        titleDetail.setText(movie.getTittle());
        ratingDetail.setText(movie.getVote_average());
        overviewDetail.setText(movie.getOverview());
        durationDetail.setText(duration);
        lengDetail.setText(movie.getOriginal_language());
        genreDetail.setText(genre);
        bannerDetail.setOnClickListener(v -> {
            Intent intent = new Intent(this, ImageViewerActivity.class);
            intent.putExtra(MOVIE_PATH, IMAGE_ORIGINAL_PATH+movie.getBackdrop_path());
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        posterDetail.setOnClickListener(v -> {
            Intent intent = new Intent(this, ImageViewerActivity.class);
            intent.putExtra(MOVIE_PATH, IMAGE_ORIGINAL_PATH+movie.getPoster_path());
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    private void setValueSimiliar() {
        SimiliarAdapter similiarAdapter = new SimiliarAdapter(this, movies);
        recyclerViewSimiliar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSimiliar.setAdapter(similiarAdapter);
    }

    private void setValueCast() {
        CastAdapter castAdapter = new CastAdapter(this, castList);
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCast.setAdapter(castAdapter);
    }

    private void setValueCrew() {
        CrewAdapter crewAdapter = new CrewAdapter(this, crewList);
        recyclerViewCrew.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewCrew.setAdapter(crewAdapter);
    }

    private void setValueTrailer() {
        List<VideoMovie> trailer = videoMovies.stream()
                .filter(videoMovie -> videoMovie.getType().equalsIgnoreCase(TRAILER))
                .collect(Collectors.toList());
        TrailerAdapter trailerAdapter = new TrailerAdapter(this, trailer);
        recyclerViewTrailer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTrailer.setAdapter(trailerAdapter);
    }
}
