package com.moviewatcher.mvw.api;

import com.moviewatcher.mvw.BuildConfig;
import com.moviewatcher.mvw.model.Movie;
import com.moviewatcher.mvw.modelapi.ResponseApi;
import com.moviewatcher.mvw.modelapi.ResponseApiGenre;
import com.moviewatcher.mvw.modelapi.ResponseApiPeople;
import com.moviewatcher.mvw.modelapi.ResponseApiVideo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String KEY = BuildConfig.ApiKey;
    @GET("trending/movie/week")
    Observable<ResponseApi> getTrending(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Observable<ResponseApi> getPopular(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/now_playing")
    Observable<ResponseApi> getNowPlaying(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/top_rated")
    Observable<ResponseApi> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movie_id}")
    Observable<Movie> getDetailMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Observable<ResponseApiPeople> getPeopleMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Observable<ResponseApiVideo> getVideoMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/similar")
    Observable<ResponseApi> getSimilar(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Observable<ResponseApi> getSearchMovie(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);

    @GET("genre/movie/list")
    Observable<ResponseApiGenre> getGenre(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<ResponseApi> getDiscover(@Query("api_key") String apiKey, @Query("page") int page, @Query("with_genres") int genre);
}
