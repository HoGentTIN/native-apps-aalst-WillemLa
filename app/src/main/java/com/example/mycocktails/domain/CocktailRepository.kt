package com.example.mycocktails.domain

import android.net.ConnectivityManager
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.network.CocktailApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CocktailRepository (private val cocktailDao: CocktailDao,
                          private val cocktailApiService: CocktailApiService,
                          private val connectivityManager: ConnectivityManager
){
    suspend fun getAllCocktails() : List<Cocktail>{
        if (connectedToInternet()){
            val cocktails = cocktailApiService.getCocktails()
            val c = cocktails.drinks
            saveToDatabase(c)
            return (c + cocktailDao.getAll()).distinct()
        }
        else{
            return cocktailDao.getAll()
        }
    }

    suspend fun delete(cocktail: Cocktail) = cocktailDao.delete(cocktail.name)
    suspend fun clear() = cocktailDao.clear()
    suspend fun insert(cocktail: Cocktail) = cocktailDao.insert(cocktail)
    suspend fun update(cocktail: Cocktail) = cocktailDao.update(cocktail)

    private fun connectedToInternet(): Boolean{
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private suspend fun saveToDatabase(cocktails: List<Cocktail>) {
        for (cocktail in cocktails) {
            cocktailDao.insert(cocktail)
        }
    }


}