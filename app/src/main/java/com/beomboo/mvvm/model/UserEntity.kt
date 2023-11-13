package com.beomboo.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// @(어노테이션)은 데이터를 설명하는 데이터 객체
// @Entity 뒤에 테이블 이름을 따로 정하지 않으면 클래스 이름을 사용하게됨
@Entity (tableName = "user")
data class UserEntity (
    @PrimaryKey val uId : String,
    @ColumnInfo(name = "uName") val name : String?,
    @ColumnInfo(name = "uPass") val pass : String?,
    @ColumnInfo(name = "uEmail") val email : String?,
)