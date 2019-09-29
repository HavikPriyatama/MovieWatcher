package com.moviewatcher.mvw.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.moviewatcher.mvw.entity.FavoriteEntity;
import com.moviewatcher.mvw.entity.MovieEntity;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    long insertFavorite(FavoriteEntity favoriteEntity);

    @Query ("SELECT c.* FROM Favorite a INNER JOIN Users b ON a.UUID = b.UUID INNER JOIN Movie c ON a.MOVIE_ID = c.MOVIE_ID WHERE b.UUID = :id")
    List<MovieEntity> getFavorite(int id);

    @Delete
    void deleteFavorite(FavoriteEntity favoriteEntity);
}
