package com.moviewatcher.mvw.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ApiClient {
    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient(Context context) {
        if (retrofit==null) {

            File cacheFile = new File(context.getCacheDir(), "okhttp.child");
            cacheFile.mkdir();

            Cache cache = new Cache(cacheFile, 10 * 1000 * 1000); //10MB

            Timber.plant(new Timber.DebugTree());

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(s -> Timber.i(s));

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .cache(cache)
                    .build();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
