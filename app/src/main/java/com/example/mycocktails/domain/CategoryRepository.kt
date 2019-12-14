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
            if (!categoryDao.getAll().value.isNullOrEmpty()) {
                return ArrayList((categoryDao.getAll().value!! + category!!.categories!!))
            }
            return ArrayList(category!!.categories!!)
        }
        if (!categoryDao.getAll().value.isNullOrEmpty()) {
            return ArrayList(categoryDao.getAll().value!!)
        }
        return arrayListOf()
    }

    private fun connectedToInternet(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private suspend fun saveToDatabase(category: List<Category>) {
        for (c in category) {
            categoryDao.insert(c)
        }
    }
}
