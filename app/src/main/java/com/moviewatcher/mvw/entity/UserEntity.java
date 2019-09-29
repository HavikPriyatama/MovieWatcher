package com.moviewatcher.mvw.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "Users")
public class UserEntity {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "UUID")
    public int id;

    @ColumnInfo (name = "USER_ID")
    public String userId;

    @ColumnInfo (name = "PASSWORD")
    public String password;

    public UserEntity(int id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }

    @Ignore
    public UserEntity(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
