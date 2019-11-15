package com.example.mycocktails.domain

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.network.CocktailApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository (private val categoryDao: CategoryDao,
                          private val cocktailApiService: CocktailApiService,
                          private val connectivityManager: ConnectivityManager
){
    suspend fun getAllCategories() : List<Category>{
        if (connectedToInternet()) {
            val category = cocktailApiService.getCategories("list")
            saveToDatabase(category.categories!!)
            if (!categoryDao.getAll().value.isNullOrEmpty()){
                return (categoryDao.getAll().value!! + category!!.categories!!)
            }
            return (category!!.categories!!)
        }
        if (!categoryDao.getAll().value.isNullOrEmpty()){
            return categoryDao.getAll().value!!
        }
        return ArrayList<Category>()
    }

    private fun connectedToInternet(): Boolean{
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private suspend fun saveToDatabase(category: List<Category>) {
        for (c in category) {
            categoryDao.insert(c)
        }
    }
}