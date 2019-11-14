package com.example.mycocktails.screens.create

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.CocktailRepository
import java.lang.IllegalArgumentException

class CreateCocktailViewModelFactory(
    private val cocktailRepository: CocktailRepository, private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateCocktailViewModel::class.java)){
            return CreateCocktailViewModel(
                cocktailRepository,
                application
            ) as T
        }
        throw IllegalArgumentException("CreateCocktailViewModelFactory - Onbekende ViewModel Class")
    }

}