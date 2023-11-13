package com.beomboo.mvvm.repository

import android.content.Context
import com.beomboo.mvvm.model.UserDatabase
import com.beomboo.mvvm.model.UserEntity

class Repository(context: Context){
    private val db = UserDatabase.getInstance(context)
    fun insert(userInfo : UserEntity) : List<Long> {
        return db?.userDao()?.insertAll(userInfo)!!
    }

    fun select(id: String, pass: String): UserEntity? {
        return db?.userDao()?.getUserInfo(id = id,pass = pass)
    }

    fun getAll(): List<UserEntity>? {
        return db?.userDao()?.getAll()
    }
}