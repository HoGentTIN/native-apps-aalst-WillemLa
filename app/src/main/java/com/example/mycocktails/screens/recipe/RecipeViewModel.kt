package com.example.mycocktails.screens.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.Cocktail
import kotlinx.coroutines.*


class RecipeViewModel(private val cocktailKey: Long, val dataSource: CocktailDao) : ViewModel() {

    private lateinit var _cocktail: LiveData<Cocktail>

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    val cocktail
        get() = _cocktail

    private suspend fun getCocktailById(cocktailKey: Long){
        _cocktail = dataSource.getCocktailWithId(cocktailKey)
    }

    init {
        uiScope.launch {
            getCocktailById(cocktailKey)
        }
    }



}