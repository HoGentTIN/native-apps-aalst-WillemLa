package com.example.mycocktails.screens.cocktail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycocktails.domain.CocktailRepository
import java.lang.IllegalArgumentException

class CocktailViewModelFactory(
    private val cocktailRepository: CocktailRepository,
    val categoryName: String? = null,
    val cocktailName: String? = null
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailViewModel::class.java)){
            return CocktailViewModel(
                cocktailRepository,
                categoryName,
                cocktailName
            ) as T
        }
        throw IllegalArgumentException("CocktailViewModelFactory - Onbekende ViewModel Class")
    }

}