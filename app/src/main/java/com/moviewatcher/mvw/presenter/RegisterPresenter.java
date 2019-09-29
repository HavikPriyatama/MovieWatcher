package com.moviewatcher.mvw.presenter;

import com.moviewatcher.mvw.activity.RegisterActivity;
import com.moviewatcher.mvw.contract.RegisterContract;
import com.moviewatcher.mvw.database.AppDatabase;
import com.moviewatcher.mvw.entity.UserEntity;

import javax.inject.Inject;

public class RegisterPresenter implements RegisterContract.Presenter {

    RegisterActivity registerActivity;
    AppDatabase appDatabase;

    @Inject
    public RegisterPresenter(RegisterActivity registerActivity, AppDatabase appDatabase) {
        this.registerActivity = registerActivity;
        this.appDatabase = appDatabase;
    }

    @Override
    public void onRegister(String userId, String password, String rePassword){
        UserEntity user = new UserEntity(userId, password);
        appDatabase.getUserDao().insertUser(user);
        registerActivity.onSuccess();
    }
}
