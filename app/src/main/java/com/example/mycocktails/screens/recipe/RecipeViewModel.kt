package com.example.mycocktails.screens.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(val recipeRepository: RecipeRepository) : ViewModel() {

    private var _cocktail: MutableLiveData<Cocktail> = MutableLiveData()
    val cocktail: LiveData<Cocktail>
        get() = _cocktail

    fun initCocktail(cocktailKey: Long) {
        viewModelScope.launch {
            _cocktail.value = recipeRepository.getAllCocktails(cocktailKey)
        }
    }
}
