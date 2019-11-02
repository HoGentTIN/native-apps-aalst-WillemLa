package com.example.mycocktails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class RecipeViewModelFactory(    private val cocktailKey: Long, private val dataSource: CocktailDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)){
            return RecipeViewModel(cocktailKey, dataSource) as T
        }
        throw IllegalArgumentException("RecipeViewModelFactory - Onbekende ViewModel Class")
    }

}