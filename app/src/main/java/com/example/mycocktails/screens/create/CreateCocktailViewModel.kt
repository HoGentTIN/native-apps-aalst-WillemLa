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
        /*if (cocktailName != null){
            database.getByCocktailName( "%" + cocktailName + "%")
        } else {
            if (categoryName != null) {
                database.getByCategory(categoryName)
            } else {
                database.getAll()
            }
        }*/

    // = database.getAll()
    // TODO houden??
    val _navigatoToCocktail = MutableLiveData<Cocktail>()

    val navigate: LiveData<Cocktail>
        get() = _navigatoToCocktail

    fun doneNavigating() {
        _navigatoToCocktail.value = null
    }

    fun addCocktail(cocktail: Cocktail) {
        uiScope.launch {
           insert(cocktail)
        }
    }

    private suspend fun getAllCocktailsFromDatabase(){
        _cocktails.value = cocktailRepository.getAllCocktails()
    }

    private suspend fun insert(cocktail: Cocktail) = cocktailRepository.insert(cocktail)
    private suspend fun delete(cocktail: Cocktail) = cocktailRepository.delete(cocktail)
    private suspend fun update(cocktail: Cocktail) = cocktailRepository.update(cocktail)
    private suspend fun clear() = cocktailRepository.clear()


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