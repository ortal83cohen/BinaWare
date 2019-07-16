package com.cohen.binaware.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cohen.binaware.models.UserContainer


@Dao
interface UserDao {

//    @get:Query("SELECT * FROM user LIMIT 1")
//    val all: List<UserContainer>
//
//    @get:Query("SELECT * FROM user LIMIT 1")
//    val liveActive: LiveData<UserContainer>
//
//    @get:Query("SELECT * FROM user LIMIT 1")
//    val active: UserContainer?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(radar: UserContainer)
//
//    @Delete
//    fun delete(radar: UserContainer)
//
//    @Query("DELETE FROM user")
//    fun deleteAll()
//
//    @Query("SELECT * FROM user WHERE id=:key LIMIT 1")
//    operator fun get(key: String): UserContainer
}

