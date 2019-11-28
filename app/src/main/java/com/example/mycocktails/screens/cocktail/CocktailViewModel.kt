package com.example.mycocktails.screens.cocktail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//verschil thread = coroutines zijn te stoppen + deze zijn light-weight
class CocktailViewModel(
    private val cocktailRepository: CocktailRepository,
    val categoryName: String?,
    val cocktailName: String?
) : ViewModel() {

    //TODO private maken!!
    private var _cocktails = MutableLiveData<List<Cocktail>>()

    private var viewModelJob = Job()

    //TODO viewModelScope is beter!!
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun getAllCocktailsFromDatabase() {
        if (categoryName != null) {
            _cocktails.value = cocktailRepository.getAllCocktailsByCategory(categoryName!!)
        } else {
            _cocktails.value = cocktailRepository.getAllCocktailsByName(cocktailName!!)
        }
    }

    init {
        //TODO aanpassen!! dat is automatischer dan een uiScope
        viewModelScope.launch {
            getAllCocktailsFromDatabase()
        }
    }

    //TODO betere afscherming!!
    val cocktails: LiveData<List<Cocktail>>
        get() = _cocktails

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