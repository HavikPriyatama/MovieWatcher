package com.moviewatcher.mvw.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.moviewatcher.mvw.dao.FavoriteDao;
import com.moviewatcher.mvw.dao.MovieDao;
import com.moviewatcher.mvw.dao.UserDao;
import com.moviewatcher.mvw.entity.FavoriteEntity;
import com.moviewatcher.mvw.entity.MovieEntity;
import com.moviewatcher.mvw.entity.UserEntity;

@Database(entities = {UserEntity.class, MovieEntity.class, FavoriteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "MovieWatcherDB";
    private static AppDatabase INSTANCE;

    public abstract UserDao getUserDao();
    public abstract FavoriteDao getFavoriteDao();
    public abstract MovieDao getMovieDao();

    public static AppDatabase getDatabaseInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
