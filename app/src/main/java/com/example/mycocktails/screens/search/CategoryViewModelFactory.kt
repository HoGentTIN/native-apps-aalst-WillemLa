package com.example.mycocktails.screens.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.domain.CategoryRepository
import java.lang.IllegalArgumentException

class CategoryViewModelFactory(private val repository: CategoryRepository, private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(
                repository,
                application
            ) as T
        }
        throw IllegalArgumentException("CategoryViewModelFactory - Onbekende ViewModel Class")
    }

}