package com.example.mycocktails.screens.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktails.domain.CategoryRepository
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import kotlinx.coroutines.launch
import java.lang.Exception

// verschil thread = coroutines zijn te stoppen + deze zijn light-weight
class CreateCocktailViewModel(val cocktailRepository: CocktailRepository, val categoryRepository: CategoryRepository) :
    ViewModel() {

    private var _categories = MutableLiveData<ArrayList<String>>()

    val categories: LiveData<ArrayList<String>>
        get() = _categories

    fun addCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            insert(cocktail)
        }
    }

    init {
        viewModelScope.launch {
            getSpinnerData()
        }
    }

    private suspend fun getSpinnerData(){
        try {
            _categories.value = ArrayList(categoryRepository.getAllCategories().map { r -> r.name })
        }
        catch (ex: Exception){ }
    }

    private suspend fun insert(cocktail: Cocktail) = cocktailRepository.insert(cocktail)
}
