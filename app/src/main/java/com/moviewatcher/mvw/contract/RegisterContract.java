package com.moviewatcher.mvw.contract;

public class RegisterContract {
    public interface View{
        void onSuccess();
        void onFailed();
    }

    public interface Presenter{
        void onRegister(String userId, String password, String rePassword);
    }
}
