package com.moviewatcher.mvw.presenter;

import com.moviewatcher.mvw.activity.ListMovieActivity;
import com.moviewatcher.mvw.api.ApiInterface;
import com.moviewatcher.mvw.contract.ListMovieContract;
import com.moviewatcher.mvw.database.AppDatabase;
import com.moviewatcher.mvw.entity.MovieEntity;
import com.moviewatcher.mvw.model.Movie;
import com.moviewatcher.mvw.modelapi.ResponseApi;
import com.moviewatcher.mvw.modelapi.ResponseApiGenre;
import com.moviewatcher.mvw.utils.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.moviewatcher.mvw.helper.Constant.FAVORITE;
import static com.moviewatcher.mvw.helper.Constant.GENRE_VIEW;
import static com.moviewatcher.mvw.helper.Constant.SEARCH_VIEW;
import static com.moviewatcher.mvw.helper.Constant.VIEW_ALL;

public class ListMoviePresenter implements ListMovieContract.Presenter {

    private ListMovieActivity listMovieActivity;
    private ApiInterface apiInterface;
    private AppDatabase appDatabase;
    private SharedPreferenceManager sharedPreferenceManager;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ListMoviePresenter(ListMovieActivity listMovieActivity, ApiInterface apiInterface, AppDatabase appDatabase, SharedPreferenceManager sharedPreferenceManager) {
        this.listMovieActivity = listMovieActivity;
        this.apiInterface = apiInterface;
        this.appDatabase = appDatabase;
        this.sharedPreferenceManager = sharedPreferenceManager;
    }

    @Override
    public void getList(String viewType, int genreId, int page, String result) {
        switch (viewType) {
            case GENRE_VIEW:
                apiInterface.getDiscover(ApiInterface.KEY, page, genreId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseApi>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(ResponseApi responseApi) {
                                listMovieActivity.onComplete(responseApi.getResult());
                            }

                            @Override
                            public void onError(Throwable e) {
                                listMovieActivity.onError("Error fetch data");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            case VIEW_ALL:
                apiInterface.getTopRated(ApiInterface.KEY, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseApi>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(ResponseApi responseApi) {
                                listMovieActivity.onComplete(responseApi.getResult());
                            }

                            @Override
                            public void onError(Throwable e) {
                                listMovieActivity.onError("Error fetch data");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            case SEARCH_VIEW:
                apiInterface.getSearchMovie(ApiInterface.KEY, result, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseApi>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(ResponseApi responseApi) {
                                listMovieActivity.onComplete(responseApi.getResult());
                            }

                            @Override
                            public void onError(Throwable e) {
                                listMovieActivity.onError("Error fetch data");
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            case FAVORITE :
                List<MovieEntity> movieEntities = appDatabase.getFavoriteDao().getFavorite(sharedPreferenceManager.getUserId());
                List<Movie> movies = new ArrayList<>();

        }
    }

    @Override
    public void getLoadMore(String viewType, int genreId, int page, String result) {
        listMovieActivity.progressBarOn();
        switch (viewType) {
            case GENRE_VIEW:
                apiInterface.getDiscover(ApiInterface.KEY, page, genreId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseApi>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(ResponseApi responseApi) {
                                listMovieActivity.onLoadMore(responseApi.getResult());
                            }

                            @Override
                            public void onError(Throwable e) {
                                listMovieActivity.onError("Error fetch data");
                            }

                            @Override
                            public void onComplete() {
                                listMovieActivity.progressBarOff();
                            }
                        });
            case VIEW_ALL:
                apiInterface.getTopRated(ApiInterface.KEY, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseApi>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(ResponseApi responseApi) {
                                listMovieActivity.onLoadMore(responseApi.getResult());
                            }

                            @Override
                            public void onError(Throwable e) {
                                listMovieActivity.onError("Error fetch data");
                            }

                            @Override
                            public void onComplete() {
                                listMovieActivity.progressBarOff();
                            }
                        });
            case SEARCH_VIEW:
                apiInterface.getSearchMovie(ApiInterface.KEY, result, page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseApi>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onNext(ResponseApi responseApi) {
                                listMovieActivity.onLoadMore(responseApi.getResult());
                            }

                            @Override
                            public void onError(Throwable e) {
                                listMovieActivity.onError("Error fetch data");
                            }

                            @Override
                            public void onComplete() {
                                listMovieActivity.progressBarOff();
                            }
                        });
        }

    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}
