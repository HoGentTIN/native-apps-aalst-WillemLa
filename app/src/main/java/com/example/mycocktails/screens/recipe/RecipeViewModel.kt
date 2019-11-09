package com.example.mycocktails.screens.recipe

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.Cocktail
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