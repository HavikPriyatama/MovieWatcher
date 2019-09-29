package com.moviewatcher.mvw.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.moviewatcher.mvw.helper.Constant.IS_LOGIN;
import static com.moviewatcher.mvw.helper.Constant.USER_ID;
import static com.moviewatcher.mvw.helper.Constant.USER_NAME;

public class SharedPreferenceManager {
    public static final String SP_APP = "spMovieWatcher";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;

    public SharedPreferenceManager(Context context) {
       sharedPreferences = context.getSharedPreferences(SP_APP, context.MODE_PRIVATE);
       spEditor = sharedPreferences.edit();
    }

    public void saveSPString(String key, String value){
        spEditor.putString(key, value);
        spEditor.commit();
    }

    public void saveSPInteger(String key, int value){
        spEditor.putInt(key, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String key, Boolean value){
        spEditor.putBoolean(key, value);
        spEditor.commit();
    }

    public String getUserName(){
        return sharedPreferences.getString(USER_NAME,"");
    }

    public int getUserId(){
        return sharedPreferences.getInt(USER_ID,0);
    }

    public Boolean getIsLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void isLogout(){
        spEditor.putBoolean(IS_LOGIN, false);
        spEditor.putString(USER_NAME, "");
        spEditor.commit();
    }

}
