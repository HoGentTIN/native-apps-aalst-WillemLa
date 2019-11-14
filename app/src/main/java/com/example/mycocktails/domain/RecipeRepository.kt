package com.example.mycocktails.domain

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.network.CocktailApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository (private val cocktailDao: CocktailDao){
    suspend fun getAllCocktails(id: Long) : Cocktail{
        return cocktailDao.getCocktailWithId(id)
    }
}