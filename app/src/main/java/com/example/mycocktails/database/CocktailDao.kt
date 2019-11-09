package com.example.mycocktails.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mycocktails.domain.Cocktail

//Data access object
@Dao
interface CocktailDao {

    @Insert
    fun insert(cocktail: Cocktail)

    @Update
    fun update(cocktail: Cocktail)

    @Query("SELECT * from personal_cocktail_table")
    fun getAll(): LiveData<List<Cocktail>>

    @Query("SELECT * from personal_cocktail_table WHERE cocktailCategory = :category")
    fun getByCategory(category: String): LiveData<List<Cocktail>>

    @Query("SELECT * from personal_cocktail_table WHERE cocktailName LIKE  :cocktailName")
    fun getByCocktailName(cocktailName: String): LiveData<List<Cocktail>>

    @Query("DELETE FROM personal_cocktail_table WHERE cocktailName = :name")
    fun delete(name: String)

    @Query("DELETE FROM personal_cocktail_table")
    fun clear()

    @Query("SELECT * from personal_cocktail_table WHERE cocktailId = :key")
    fun getCocktailWithId(key: Long): LiveData<Cocktail>
}