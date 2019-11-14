package com.example.mycocktails.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mycocktails.domain.Cocktail

//Data access object
@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cocktail: Cocktail)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(cocktail: Cocktail)

    @Query("SELECT * from personal_cocktail_table")
    suspend fun getAll(): List<Cocktail>

    @Query("SELECT * from personal_cocktail_table WHERE cocktailCategory = :category")
    suspend fun getByCategory(category: String): List<Cocktail>

    @Query("SELECT * from personal_cocktail_table WHERE cocktailName LIKE  :cocktailName")
    suspend fun getByCocktailName(cocktailName: String): List<Cocktail>

    @Query("DELETE FROM personal_cocktail_table WHERE cocktailName = :name")
    fun delete(name: String)

    @Query("DELETE FROM personal_cocktail_table")
    fun clear()

    @Query("SELECT * from personal_cocktail_table WHERE cocktailId = :key")
    suspend fun getCocktailWithId(key: Long): Cocktail
}