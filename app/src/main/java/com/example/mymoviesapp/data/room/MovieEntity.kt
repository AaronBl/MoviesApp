package com.example.mymoviesapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title")val title: String?,
    @ColumnInfo(name = "description") var description: String?,
    @ColumnInfo(name = "img_url")var img: String?
)
