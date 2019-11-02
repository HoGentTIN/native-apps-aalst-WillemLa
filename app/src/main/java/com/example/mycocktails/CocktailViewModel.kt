package com.example.mycocktails

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


//verschil thread = coroutines zijn te stoppen + deze zijn light-weight
class CocktailViewModel(val database: CocktailDao, application: Application, val categoryName: String?, val cocktailName: String?) :
    AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var cocktails =
        if (cocktailName != null){
            database.getByCocktailName( "%" + cocktailName!! + "%")
        } else {
            if (categoryName != null) {
                database.getByCategory(categoryName!!)
            } else {
                database.getAll()
            }
        }


    // = database.getAll()
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

    private suspend fun getAllCocktailsFromDatabase(): LiveData<List<Cocktail>> {
        return withContext(Dispatchers.IO) {
            var cocktails = database.getAll()
            cocktails
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun delete(cocktail: Cocktail) {
        withContext(Dispatchers.IO) {
            database.insert(cocktail)
        }
    }

    private suspend fun update(cocktail: Cocktail) {
        withContext(Dispatchers.IO) {
            database.update(cocktail)
        }
    }

    private suspend fun insert(cocktail: Cocktail) {
        withContext(Dispatchers.IO) {
            database.insert(cocktail)
        }
    }

    private suspend fun initDb() {

        withContext(Dispatchers.IO) {
            var c = Cocktail( )
            c.name = "Margarita"
            c.category = "Ordinary Drink"
            insert(c)
            var c2 = Cocktail()
            c2.name = "Margarita23"
            c2.category = "Ordinary Drink"
            insert(c2)
        }
    }

    /*
    fun setData(nameCategory: String?, nameCocktail: String?){
        uiScope.launch {
            withContext(Dispatchers.IO) {
                if (nameCategory != null){
                    cocktails = database.getByCategory(nameCategory)
                }
                if (nameCocktail != null){
                    cocktails = database.getByCocktailName(nameCocktail)
                }
            }
        }
    }*/

    init {
        uiScope.launch {
            initDb()
           // setData(categoryName, cocktailName)
        }
    }

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