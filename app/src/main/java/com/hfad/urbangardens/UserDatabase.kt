package com.hfad.urbangardens

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, AddItem::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun addItemDao(): AddItemDao
}