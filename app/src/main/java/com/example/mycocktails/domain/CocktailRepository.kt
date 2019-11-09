package com.example.mycocktails.domain

import android.net.ConnectivityManager
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.network.CocktailApiService

class CocktailRepository (private val cocktailDao: CocktailDao,
                          private val cocktailApiService: CocktailApiService,
                          private val connectivityManager: ConnectivityManager
){
    suspend fun getAllCocktails() : List<Cocktail>{
        if (connectedToInternet()){
            val cocktails = cocktailApiService.getCocktails()
            saveToDatabase(cocktails)
            return (cocktails + cocktailDao.getAll() as List<Cocktail>).distinct()
        }
        else{
            return cocktailDao.getAll() as List<Cocktail>
        }
    }
    suspend fun insert(cocktail: Cocktail) = cocktailDao.insert(cocktail)

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