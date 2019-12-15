package com.example.mycocktails.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.CategoryRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    private var _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    init {
        viewModelScope.launch {
            _categories.value = categoryRepository.getAllCategories()
        }
    }

    private val _navigateToCategory = MutableLiveData<String>()
    val navigateToCategory: LiveData<String>
        get() = _navigateToCategory

    fun onCategoryClicked(name: String) {
        _navigateToCategory.value = name
    }

    fun onNavigated() {
        _navigateToCategory.value = null
    }
}
