package com.moviewatcher.mvw.presenter;

import com.moviewatcher.mvw.api.ApiInterface;
import com.moviewatcher.mvw.contract.FragmentHomeContract;
import com.moviewatcher.mvw.fragment.FragmentHome;
import com.moviewatcher.mvw.modelapi.ResponseApi;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class FragmentHomePresenter implements FragmentHomeContract.Presenter {

    private FragmentHome fragmentHome;
    private ApiInterface apiInterface;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public FragmentHomePresenter(FragmentHome fragmentHome, ApiInterface apiInterface) {
        this.fragmentHome = fragmentHome;
        this.apiInterface = apiInterface;
    }

    @Override
    public void getTrending() {
        apiInterface.getTrending(ApiInterface.KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseApi responseApi) {
                        fragmentHome.displayTrendingMovie(responseApi.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentHome.onError("Error fetch data");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getTopRated() {
        apiInterface.getPopular(ApiInterface.KEY,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseApi responseApi) {
                        fragmentHome.displayTopRatedMovie(responseApi.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentHome.onError("Error fetch data");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getPopular(int page) {
        apiInterface.getPopular(ApiInterface.KEY, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseApi responseApi) {
                        fragmentHome.displayPopularMovie(responseApi.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentHome.onError("Error fetch data");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadMorePopular(int page) {
        fragmentHome.showLoading();
        apiInterface.getPopular(ApiInterface.KEY, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseApi responseApi) {
                        fragmentHome.loadMorePopular(responseApi.getResult());
                        fragmentHome.stopLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        fragmentHome.onError("Error fetch data");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onDestroy() {
        disposable.dispose();
    }
}
