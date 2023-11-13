package com.beomboo.mvvm.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

// Data Access Object
@Dao
interface UserDao {
    @Query("SELECT * " +
            "FROM user")
    fun getAll(): List<UserEntity>

    @Query("SELECT * " +
            "FROM user " +
            "WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<UserEntity>

    @Query("SELECT * " +
            "FROM user " +
            "WHERE uid = :id " +
            "AND uPass = :pass " +
            "LIMIT 1")
    fun getUserInfo(id: String, pass: String): UserEntity

    @Insert
    fun insertAll(vararg userEntities: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

}