package com.example.mycocktails.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mycocktails.domain.Category

// Data access object
@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(category: Category)

    @Query("SELECT * from personal_category_table")
    fun getAll(): LiveData<List<Category>>

    @Query("SELECT * from personal_category_table WHERE categoryName = :category")
    fun get(category: String): LiveData<List<Category>>

    @Query("DELETE FROM personal_category_table WHERE categoryName = :name")
    fun delete(name: String)

    @Query("DELETE FROM personal_category_table")
    fun clear()
}
