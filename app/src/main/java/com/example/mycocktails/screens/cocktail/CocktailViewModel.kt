package com.example.mycocktails.screens.cocktail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import kotlinx.coroutines.launch

// verschil thread = coroutines zijn te stoppen + deze zijn light-weight
class CocktailViewModel(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {

    private var _cocktails = MutableLiveData<List<Cocktail>>()

    val cocktails: LiveData<List<Cocktail>>
        get() = _cocktails

    fun getAllCocktailsFromDatabase(categoryName: String?, cocktailName: String?) {
        viewModelScope.launch {
            if (categoryName != null) {
                _cocktails.value = cocktailRepository.getAllCocktailsByCategory(categoryName!!)
            } else {
                _cocktails.value = cocktailRepository.getAllCocktailsByName(cocktailName!!)
            }
        }
    }

    private val _navigateToCocktail = MutableLiveData<Long>()
    val navigateToCocktail
        get() = _navigateToCocktail

    fun onCocktailClicked(id: Long) {
        _navigateToCocktail.value = id
    }

    fun onNavigated() {
        _navigateToCocktail.value = null
    }
}
