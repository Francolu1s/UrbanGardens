package com.hfad.urbangardens

import androidx.room.*

@Dao
interface AddItemDao {
        @Insert
        fun insertItem(addItem: AddItem)

        @Query("SELECT * FROM add_item_table WHERE userId = :userId")
        fun getItemsByUser(userId: Long): List<AddItem>
}