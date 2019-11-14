package com.example.mycocktails.screens.search

import android.app.Application
import androidx.lifecycle.*
import com.example.mycocktails.R
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.domain.Category
import kotlinx.coroutines.*

class SearchViewModel(val database: CategoryDao, application: Application) :   AndroidViewModel(application) {


    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val category = database.getAll()

    fun addCocktail(category: Category) {
        uiScope.launch {
            insert(category)
        }
    }

    private suspend fun getAllCocktailsFromDatabase(): LiveData<List<Category>> {
        return withContext(Dispatchers.IO) {
            var category = database.getAll()
            category
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun delete(category: Category) {
        withContext(Dispatchers.IO) {
            database.insert(category)
        }
    }

    private suspend fun update(category: Category) {
        withContext(Dispatchers.IO) {
            database.update(category)
        }
    }

    private suspend fun insert(category: Category) {
        withContext(Dispatchers.IO) {
            database.insert(category)
        }
    }

    init {
        uiScope.launch {
            clear()
            addCocktail(Category("Ordinary Drink"))
            addCocktail(Category("Cocktail"))
            addCocktail(Category("Milk / Float / Shake"))
            addCocktail(Category("Other/Unknown"))
            addCocktail(Category("Cocoa"))
            addCocktail(Category("Shot"))
            addCocktail(Category("Coffee / Tea"))
            addCocktail(Category("Homemade Liquer"))
            addCocktail(Category("Punch / Party Drink"))
            addCocktail(Category("Beer"))
            addCocktail(Category("Soft Drink / Soda"))
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


    fun getImageResource(categoryName: String): Int {
        when (categoryName) {
            "Ordinary Drink" -> return R.drawable.ordinary
            "Cocktail" -> return R.drawable.cocktail
            "Milk / Float / Shake" -> return R.drawable.milk
            "Other/Unknown" -> return R.drawable.other
            "Cocoa" -> return R.drawable.cocoa
            "Shot" -> return R.drawable.shot
            "Coffee / Tea" -> return R.drawable.thee
            "Homemade Liquer" -> return R.drawable.self
            "Punch / Party Drink" -> return R.drawable.party
            "Beer" -> return R.drawable.bier
            "Soft Drink / Soda" -> return R.drawable.soda
            else -> return R.drawable.extra
        }
    }
}