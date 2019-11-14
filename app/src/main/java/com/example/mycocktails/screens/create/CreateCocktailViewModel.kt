package com.example.mycocktails.screens.create

import android.app.Application
import androidx.lifecycle.*
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import kotlinx.coroutines.*

//verschil thread = coroutines zijn te stoppen + deze zijn light-weight
class CreateCocktailViewModel(val cocktailRepository: CocktailRepository, application: Application) :
    AndroidViewModel(application) {

    var _cocktails = MutableLiveData<List<Cocktail>>()

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addCocktail(cocktail: Cocktail) {
        uiScope.launch {
            insert(cocktail)
        }
    }

    private suspend fun insert(cocktail: Cocktail) = cocktailRepository.insert(cocktail)

}