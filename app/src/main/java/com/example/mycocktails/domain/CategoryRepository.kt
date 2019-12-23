package com.example.mycocktails.domain

import android.net.ConnectivityManager
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.network.CocktailApiService

class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val cocktailApiService: CocktailApiService,
    private val connectivityManager: ConnectivityManager
) {
    suspend fun getAllCategories(): ArrayList<Category> {
        if (connectedToInternet()) {
            val category = cocktailApiService.getCategories("list")

            saveToDatabase(category.categories!!)
            return ArrayList(category!!.categories!!)
        }
        if (!categoryDao.getAll().isNullOrEmpty()) {
            return ArrayList(categoryDao.getAll())
        }
        return arrayListOf()
    }

    fun connectedToInternet(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private suspend fun saveToDatabase(category: List<Category>?) {
        if (category != null) {
            categoryDao.clear()
            for (c in category) {
                categoryDao.insert(c)
            }
        }
    }
}
