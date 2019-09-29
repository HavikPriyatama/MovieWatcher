package com.moviewatcher.mvw.presenter;

import com.moviewatcher.mvw.api.ApiInterface;
import com.moviewatcher.mvw.contract.FragmentGenreContract;
import com.moviewatcher.mvw.fragment.FragmentGenre;
import com.moviewatcher.mvw.helper.ListBackDrop;
import com.moviewatcher.mvw.model.Genre;
import com.moviewatcher.mvw.modelapi.ResponseApiGenre;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FragmentGenrePresenter implements FragmentGenreContract.Presenter {

    private FragmentGenre fragmentGenre;
    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String[] listBackDrop = ListBackDrop.listBackDrop;

    @Inject
    public FragmentGenrePresenter(FragmentGenre fragmentGenre, ApiInterface apiInterface) {
        this.fragmentGenre = fragmentGenre;
        this.apiInterface = apiInterface;
    }

    @Override
    public void getGenre() {
        apiInterface.getGenre(ApiInterface.KEY)
                .subscribeOn(Schedulers.io())
                .map(responseApiGenre -> {
                    List<Genre> genreList = responseApiGenre.getGenres();
                    for (int i = 0; i < genreList.size(); i++) {
                        genreList.get(i).setBackdropImage(listBackDrop[i]);
                    }
                    return genreList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Genre>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<Genre> genres) {
                        fragmentGenre.setGenreAdapter(genres);
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentGenre.onError("Error fetch data");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}
