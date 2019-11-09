package com.example.mycocktails.screens.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycocktails.database.CategoryDao
import java.lang.IllegalArgumentException

class CategoryViewModelFactory(private val dataSource: CategoryDao, private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("CategoryViewModelFactory - Onbekende ViewModel Class")
    }

}