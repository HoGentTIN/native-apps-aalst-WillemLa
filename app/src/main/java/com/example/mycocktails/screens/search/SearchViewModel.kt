package com.example.mycocktails.screens.search

import android.app.Application
import androidx.lifecycle.*
import com.example.mycocktails.R
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.CategoryRepository
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import kotlinx.coroutines.*

class SearchViewModel(val categoryRepository: CategoryRepository, application: Application) :   AndroidViewModel(application) {


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //val category = database.getAll()
    var _categories = MutableLiveData<List<Category>>()

    val categories
        get() = _categories



    private suspend fun getAllCategoriesFromDatabase(){
        _categories.value = categoryRepository.getAllCategories()
    }
    
    init {
        uiScope.launch {
            getAllCategoriesFromDatabase()
        }
    }

    private val _navigateToCategory = MutableLiveData<String>()
    val navigateToCategory
        get() = _navigateToCategory


    fun onCategoryClicked(name:String){
        navigateToCategory.value = name
    }

    fun onNavigated(){
        navigateToCategory.value = null
    }
}