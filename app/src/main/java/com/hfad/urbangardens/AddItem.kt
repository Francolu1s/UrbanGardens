package com.hfad.urbangardens

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_item_table")
data class AddItem (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val item:String,
    val unitsBought: Double,
    val unitPrice:Double,
    val month: Int,
    val timestamp: Long
    )