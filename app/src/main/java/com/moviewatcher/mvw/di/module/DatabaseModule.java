package com.moviewatcher.mvw.di.module;

import android.app.Application;

import androidx.room.Room;

import com.moviewatcher.mvw.database.AppDatabase;
import com.moviewatcher.mvw.utils.SharedPreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.moviewatcher.mvw.helper.Constant.DB_NAME;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Application application){
        return Room.databaseBuilder(application, AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    SharedPreferenceManager sharedPreferenceManager(Application application){
        return new SharedPreferenceManager(application);
    }
}
