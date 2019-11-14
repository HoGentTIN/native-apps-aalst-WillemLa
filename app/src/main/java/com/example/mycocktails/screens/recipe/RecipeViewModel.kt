package com.example.mycocktails.screens.recipe

import androidx.lifecycle.*
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.domain.RecipeRepository
import com.example.mycocktails.formatIngredients
import kotlinx.coroutines.*
import okhttp3.Dispatcher


class RecipeViewModel(val recipeRepository: CocktailDao, private val cocktailKey: Long, val dataSource: CocktailDao) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var cocktail: MutableLiveData<Cocktail> = MutableLiveData()

    suspend fun initCocktail(){
        withContext(Dispatchers.Main) {
            cocktail.value = recipeRepository.getCocktailWithId(cocktailKey)// .getAllCocktails(cocktailKey)
        }
    }

    init {
        MainScope().launch {
            initCocktail()
        }
    }
}

