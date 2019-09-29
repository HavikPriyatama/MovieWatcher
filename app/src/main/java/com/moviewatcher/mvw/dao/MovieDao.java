package com.moviewatcher.mvw.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.moviewatcher.mvw.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    long insertMovie(MovieEntity movieEntity);

}
