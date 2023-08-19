package com.hfad.urbangardens
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val email: String,
    val nickname: String,
    val password: String,
    val isEnabled: Boolean,
    val itemsComsumption: Double = 0.0
)