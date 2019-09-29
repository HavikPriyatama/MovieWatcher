package com.moviewatcher.mvw.presenter;

import com.moviewatcher.mvw.activity.LoginActivity;
import com.moviewatcher.mvw.contract.LoginContract;
import com.moviewatcher.mvw.database.AppDatabase;
import com.moviewatcher.mvw.entity.UserEntity;
import com.moviewatcher.mvw.utils.SharedPreferenceManager;

import javax.inject.Inject;

import static com.moviewatcher.mvw.helper.Constant.IS_LOGIN;
import static com.moviewatcher.mvw.helper.Constant.USER_ID;
import static com.moviewatcher.mvw.helper.Constant.USER_NAME;

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";
    private AppDatabase appDatabase;
    private LoginActivity loginActivity;
    private SharedPreferenceManager sharedPreferenceManager;

    @Inject
    public LoginPresenter(AppDatabase appDatabase, LoginActivity loginActivity, SharedPreferenceManager sharedPreferenceManager) {
        this.appDatabase = appDatabase;
        this.loginActivity = loginActivity;
        this.sharedPreferenceManager = sharedPreferenceManager;
    }

    @Override
    public void doLogin(String userId, String password) {
        UserEntity user = appDatabase.getUserDao().getUser(userId, password);
        if (user == null){
            loginActivity.onNotFound();
        }else{
            sharedPreferenceManager.saveSPString(USER_NAME, user.getUserId());
            sharedPreferenceManager.saveSPInteger(USER_ID, user.getId());
            sharedPreferenceManager.saveSPBoolean(IS_LOGIN, true);
            loginActivity.onSuccess();
        }
    }
}
