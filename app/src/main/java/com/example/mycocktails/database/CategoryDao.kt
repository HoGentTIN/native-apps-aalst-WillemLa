package com.example.mycocktails.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mycocktails.domain.Category

//Data access object
@Dao
interface CategoryDao {

    @Insert
    fun insert(category: Category)

    @Update
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