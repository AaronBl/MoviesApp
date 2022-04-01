package com.example.mymoviesapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.firestore.auth.User


@Database(entities = [MovieEntity::class], version = 4)
abstract class DataBaseMovie : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {

        private const val DATABASE_NAME = "movies"

        @Synchronized
        fun getDatabase(context: Context): DataBaseMovie = Room.databaseBuilder(
            context.applicationContext,
            DataBaseMovie::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}