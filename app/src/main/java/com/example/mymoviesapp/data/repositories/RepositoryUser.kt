package com.example.mymoviesapp.data.repositories


import com.example.mymoviesapp.data.UserDataSource
import com.example.mymoviesapp.data.room.UserEntity

class RepositoryUser(
    private val userDataSource: UserDataSource
) {
    suspend fun insertUser(user: UserEntity) : Long{
        return userDataSource.insertUser(user)
    }

    suspend fun getUsers() : List<UserEntity> {
        return userDataSource.getUser()
    }

}