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
    fun getAll(): LiveData<List<Cocktail>>

    @Query("SELECT * from personal_cocktail_table WHERE cocktailCategory = :category")
    fun getByCategory(category: String): LiveData<List<Cocktail>>

    @Query("SELECT * from personal_cocktail_table WHERE cocktailName LIKE  :cocktailName")
    fun getByCocktailName(cocktailName: String): LiveData<List<Cocktail>>

    @Query("DELETE FROM personal_cocktail_table WHERE cocktailName = :name")
    suspend fun delete(name: String)

    @Query("DELETE FROM personal_cocktail_table")
    suspend fun clear()

    @Query("SELECT * from personal_cocktail_table WHERE cocktailId = :key")
    fun getCocktailWithId(key: Long): LiveData<Cocktail>
}