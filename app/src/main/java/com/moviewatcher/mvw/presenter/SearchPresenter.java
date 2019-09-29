package com.moviewatcher.mvw.presenter;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.moviewatcher.mvw.activity.SearchActivity;
import com.moviewatcher.mvw.api.ApiInterface;
import com.moviewatcher.mvw.contract.SearchContract;
import com.moviewatcher.mvw.modelapi.ResponseApi;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchActivity searchActivity;
    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SearchPresenter(SearchActivity searchActivity, ApiInterface apiInterface) {
        this.searchActivity = searchActivity;
        this.apiInterface = apiInterface;
    }

    @Override
    public void onTextChange(String value) {
        if (!value.isEmpty()){
            fetchResult(value);
        }else{
            searchActivity.setSearchList(Collections.emptyList());
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    private void fetchResult(String value) {
        apiInterface.getSearchMovie(ApiInterface.KEY, value, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseApi responseApi) {
                        searchActivity.setSearchList(responseApi.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchActivity.onError("Error fetch data");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
