package com.moviewatcher.mvw.contract;

import com.moviewatcher.mvw.entity.UserEntity;

public class LoginContract {
    public interface View{
        void onSuccess();
        void onNotFound();
        void onError();
    }

    public interface Presenter{
        void doLogin(String userId, String password);
    }
}
