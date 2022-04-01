package com.example.mymoviesapp.data


import com.example.mymoviesapp.data.room.DataBaseUser
import com.example.mymoviesapp.data.room.UserEntity

class LocalUserDataSource(db: DataBaseUser) : UserDataSource {

    private val dbUser by lazy { db.userDao() }

    override suspend fun insertUser(user: UserEntity): Long {
        return dbUser.insertUser(user)
    }

    override suspend fun getUser(): List<UserEntity> {
        return dbUser.getAllUser()
    }


}