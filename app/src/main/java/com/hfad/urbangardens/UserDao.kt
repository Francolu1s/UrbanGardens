package com.hfad.urbangardens

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM user_table WHERE nickname=:nickname AND password=:password")
   fun getUserByCredentials(nickname: String, password: String): User?

    @Update
    suspend fun updateUser(user: User)
}