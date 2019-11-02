package com.example.mycocktails

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CocktailViewModelFactory(
    private val dataSource: CocktailDao, private val application: Application, val categoryName: String? = null, val cocktailName: String? = null): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailViewModel::class.java)){
            return CocktailViewModel(dataSource, application, categoryName, cocktailName) as T
        }
        throw IllegalArgumentException("CocktailViewModelFactory - Onbekende ViewModel Class")
    }

}