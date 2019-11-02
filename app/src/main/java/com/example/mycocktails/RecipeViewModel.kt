package com.example.mycocktails

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

class RecipeViewModel(private val cocktailKey: Long = 0L, dataSource: CocktailDao) : ViewModel() {

    val database = dataSource
    private val viewModelJob = Job()
    private val cocktail = MediatorLiveData<Cocktail>()

    fun getCocktail() = cocktail

    init {
        cocktail.addSource(database.getCocktailWithId(cocktailKey), cocktail::setValue)
    }

}