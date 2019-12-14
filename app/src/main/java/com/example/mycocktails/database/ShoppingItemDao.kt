package com.example.mycocktails.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycocktails.domain.ShoppingItem

// Data access object
@Dao
interface ShoppingItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(shoppingItem: ShoppingItem)

    @Query("SELECT * from personal_shoppingItem_table")
    suspend fun getAll(): List<ShoppingItem>

    @Query("DELETE FROM personal_shoppingItem_table WHERE shoppingItemId = :shoppingItemId")
    suspend fun delete(shoppingItemId: Long)

    @Query("DELETE FROM personal_shoppingItem_table")
    suspend fun clear()
}
