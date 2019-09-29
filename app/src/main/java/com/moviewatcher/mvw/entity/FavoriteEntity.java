package com.moviewatcher.mvw.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "Favorite",
        foreignKeys = {
            @ForeignKey(entity = MovieEntity.class, parentColumns = "MOVIE_ID", childColumns = "MOVIE_ID"),
                @ForeignKey(entity = UserEntity.class, parentColumns = "UUID", childColumns = "UUID")
        },
        indices = {@Index(value = {"MOVIE_ID","MOVIE_ID"}), @Index(value = {"UUID","UUID"})}
)
public class FavoriteEntity {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "FAVORITE_ID")
    public int id;

    @ColumnInfo (name = "UUID")
    public int userId;

    @ColumnInfo (name = "MOVIE_ID")
    public int movieId;
}
