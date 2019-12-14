package com.example.mycocktails.screens.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycocktails.domain.ShoppingItem
import com.example.mycocktails.domain.ShoppingItemRepository
import kotlinx.coroutines.launch

class ShoppingItemViewModel(val shoppingItemRepository: ShoppingItemRepository) : ViewModel() {

    fun insert(shoppingItem: ShoppingItem) {
        viewModelScope.launch {
            shoppingItemRepository.insert(shoppingItem)
            getShoppingItems()
        }
    }
    fun delete(shoppingItemId: Long) {
        viewModelScope.launch {
            shoppingItemRepository.delete(shoppingItemId)
            getShoppingItems()
        }
    }

    fun clear() {
        viewModelScope.launch {
            shoppingItemRepository.clear()
            getShoppingItems()
        }
    }

    private var _shoppingItems = MutableLiveData<List<ShoppingItem>>()

    private suspend fun getShoppingItems() {
        _shoppingItems.value = shoppingItemRepository.getAll()
    }

    init {
        viewModelScope.launch {
            getShoppingItems()
        }
    }

    val shoppingItems: LiveData<List<ShoppingItem>>
        get() = _shoppingItems

    private val _navigateToShoppingItem = MutableLiveData<Long>()
    val deleteShoppingItem
        get() = _navigateToShoppingItem

    fun onShoppingItemClicked(id: Long) {
        _navigateToShoppingItem.value = id
    }

    fun onNavigated() {
        _navigateToShoppingItem.value = null
    }
}
