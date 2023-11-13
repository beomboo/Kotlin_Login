package com.beomboo.mvvm.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.beomboo.mvvm.model.UserDatabase
import com.beomboo.mvvm.model.UserEntity
import kotlin.random.Random

class Repository(context: Context){
    private val db = UserDatabase.getInstance(context)
    fun insert(userInfo : UserEntity) : Boolean{
        db?.userDao()?.insertAll(userInfo).run { return true }
    }

    fun select(id: String, pass: String): UserEntity? {
        return db?.userDao()?.getUserInfo(id = id,pass = pass)
    }

    fun getAll(): List<UserEntity>? {
        return db?.userDao()?.getAll()
    }
}