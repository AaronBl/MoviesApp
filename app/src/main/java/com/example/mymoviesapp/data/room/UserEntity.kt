package com.example.mymoviesapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val num: Int,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "phone") var phone: String = "",
    @ColumnInfo(name = "email") var email: String = ""
)