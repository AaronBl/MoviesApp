package com.example.mymoviesapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 3)
abstract class DataBaseUser : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME = "users"

        @Synchronized
        fun getDatabase(context: Context): DataBaseUser = Room.databaseBuilder(
            context.applicationContext,
            DataBaseUser::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}