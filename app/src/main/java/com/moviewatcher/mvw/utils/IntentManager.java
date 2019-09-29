package com.moviewatcher.mvw.utils;

import android.content.Context;
import android.content.Intent;

import com.moviewatcher.mvw.activity.DetailMovieActivity;
import com.moviewatcher.mvw.activity.ListMovieActivity;

import static com.moviewatcher.mvw.helper.Constant.APPBAR_TITLE;
import static com.moviewatcher.mvw.helper.Constant.GENRE_ID;
import static com.moviewatcher.mvw.helper.Constant.LIST_TYPE;
import static com.moviewatcher.mvw.helper.Constant.MOVIE_ID;
import static com.moviewatcher.mvw.helper.Constant.SEARCH_RESULT;

public class IntentManager {
    private Context context;

    public IntentManager(Context context) {
        this.context = context;
    }

    public void noHistoryIntent(Class activityClass){
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY | intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void newTaskIntent(Class activityClass){
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void clearStackIntent(Class activityClass){
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void getDetailMovie(int id){
        Intent intent = new Intent(context, DetailMovieActivity.class);
        intent.putExtra(MOVIE_ID, id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void getListMovie(int id, String result,String title, String listType){
        Intent intent = new Intent(context, ListMovieActivity.class);
        intent.putExtra(LIST_TYPE, listType);
        intent.putExtra(APPBAR_TITLE, title);
        intent.putExtra(GENRE_ID, id);
        intent.putExtra(SEARCH_RESULT, result);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
