package com.example.mycocktails.screens.cocktail

import android.app.Application
import androidx.lifecycle.*
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import kotlinx.coroutines.*

//verschil thread = coroutines zijn te stoppen + deze zijn light-weight
class CocktailViewModel(val cocktailRepository: CocktailRepository, application: Application, val categoryName: String?, val cocktailName: String?) :
    AndroidViewModel(application) {

    var _cocktails = MutableLiveData<List<Cocktail>>()

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun getAllCocktailsFromDatabase(){
        if (categoryName != null){
            _cocktails.value = cocktailRepository.getAllCocktailsByCategory(categoryName!!)
        }
        else{
            _cocktails.value = cocktailRepository.getAllCocktailsByName(cocktailName!!)
        }
    }

    init {
        uiScope.launch {
            getAllCocktailsFromDatabase()
        }
    }

    val cocktails
        get() = _cocktails

    private val _navigateToCocktail = MutableLiveData<Long>()
    val navigateToCocktail
    get() = _navigateToCocktail

    fun onCocktailClicked(id:Long){
        _navigateToCocktail.value = id
    }

    fun onNavigated(){
        _navigateToCocktail.value = null
    }

}