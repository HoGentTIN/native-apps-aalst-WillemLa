package com.example.mycocktails.domain

import android.net.ConnectivityManager
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.network.CocktailApiService

class CocktailRepository(
    private val cocktailDao: CocktailDao,
    private val cocktailApiService: CocktailApiService,
    private val connectivityManager: ConnectivityManager
) {

    /*
    * Alle cocktails ophalen: geeft een object weer met array. Deze objecten (= cocktail) bevatten enkel de naam en een id, geen extra informatie.
    * Voor elke object in array (= cocktail) wordt een individuale API call uitgevoerd om de cocktail en alle bijhourende informatie te krijgen
    * */
    suspend fun getAllCocktailsByCategory(categoryName: String): List<Cocktail> {
        if (connectedToInternet()) {
            val drink = cocktailApiService.getCocktails(categoryName)
            var cocktails = mutableListOf<Cocktail>()
            drink.drinks!!.forEach {
                cocktail ->
                var cocktailToAdd: Cocktail = cocktailApiService.getCocktailsById(cocktail.cocktailId.toString()).drinks!![0]
                cocktails.add(cocktailToAdd)
            }
            saveToDatabase(cocktails)
            return (cocktailDao.getByCategory(categoryName) + cocktails).distinct()
        }
        return cocktailDao.getAll()
    }

    suspend fun getAllCocktailsByName(cocktailName: String): List<Cocktail> {
        if (connectedToInternet()) {
            val drink: Drinks? = cocktailApiService.getCocktailsByName(cocktailName)
            if (drink != null && !drink.drinks.isNullOrEmpty()) {
                var cocktails = arrayListOf<Cocktail>()
                drink!!.drinks!!.forEach { cocktail ->
                    var cocktailToAdd: Cocktail =
                        cocktailApiService.getCocktailsById(cocktail.cocktailId.toString()).drinks!![0]
                    cocktails.add(cocktailToAdd)
                }
                saveToDatabase(cocktails)
                return (cocktailDao.getByCocktailName(cocktailName)!! + cocktails)
            }
        }
        return cocktailDao.getAll()
    }

    suspend fun insert(cocktail: Cocktail) {
        if (cocktailDao.getByCocktailName(cocktail.name).isNullOrEmpty()) {
            cocktailDao.insert(cocktail)
        }
    }

    private fun connectedToInternet(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private suspend fun saveToDatabase(cocktails: List<Cocktail>) {
        for (cocktail in cocktails) {
            cocktailDao.insert(cocktail)
        }
    }
}
