package com.example.mycocktails.domain

import com.example.mycocktails.database.ShoppingItemDao

class ShoppingItemRepository(private val shoppingItemDao: ShoppingItemDao) {

    suspend fun insert(shoppingItem: ShoppingItem) = shoppingItemDao.insert(shoppingItem)
    suspend fun delete(shoppingItemId: Long) = shoppingItemDao.delete(shoppingItemId)
    suspend fun clear() = shoppingItemDao.clear()
    suspend fun getAll() = shoppingItemDao.getAll()
}
