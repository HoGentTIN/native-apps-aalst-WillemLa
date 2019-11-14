package com.example.mycocktails.screens.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.domain.RecipeRepository
import java.lang.IllegalArgumentException

class RecipeViewModelFactory(val recipeRepository: CocktailDao, private val cocktailKey: Long, private val dataSource: CocktailDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)){
            return RecipeViewModel(
                recipeRepository,
                cocktailKey,
                dataSource
            ) as T
        }
        throw IllegalArgumentException("RecipeViewModelFactory - Onbekende ViewModel Class")
    }

}