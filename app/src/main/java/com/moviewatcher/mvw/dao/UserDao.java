package com.moviewatcher.mvw.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.moviewatcher.mvw.entity.UserEntity;

@Dao
public interface UserDao {

    @Insert
    void insertUser(UserEntity userEntity);

    @Query("SELECT * FROM Users WHERE USER_ID = :userId " + "AND PASSWORD = :password")
    UserEntity getUser(String userId, String password);

}
