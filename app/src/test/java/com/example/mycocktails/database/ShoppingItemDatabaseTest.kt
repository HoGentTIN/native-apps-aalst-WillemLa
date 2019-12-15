package com.example.mycocktails.database

import com.example.mycocktails.domain.ShoppingItem
import com.example.mycocktails.domain.ShoppingItemRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class ShoppingItemDatabaseTest {

    private val shoppingItemDao: ShoppingItemDao = mockk()
    private lateinit var repository: ShoppingItemRepository

    private val shoppingItem1: ShoppingItem = mockk()
    private val shoppingItem2: ShoppingItem = mockk()
    private val shoppingItem3: ShoppingItem = mockk()
    private val shoppingItem4: ShoppingItem = mockk()
    private val shoppingItem5: ShoppingItem = mockk()
    private val shoppingItem1Id = 1L

    private val shoppingItems = arrayListOf<ShoppingItem>(shoppingItem1, shoppingItem2, shoppingItem3, shoppingItem4)

    @Before
    fun createDb() {
        repository = ShoppingItemRepository(shoppingItemDao)
        coEvery { repository.getAll() } returns shoppingItems
        coEvery { shoppingItem1.shoppingItemId } returns shoppingItem1Id
        coEvery { shoppingItemDao.getAll() } returns shoppingItems
        coEvery { shoppingItemDao.delete(shoppingItem1Id) } returns Unit
        coEvery { shoppingItemDao.clear() } returns Unit
        coEvery { shoppingItemDao.insert(shoppingItem5) } returns Unit
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun getAllShoppingItems() {
        runBlocking {
            val items = repository.getAll()
            assertEquals(items, shoppingItems)
            assertEquals(4, items.size)
            coVerify { shoppingItemDao.getAll() }
        }
    }

    @Test
    fun insertShoppingItem() {
        runBlocking {
            repository.insert(shoppingItem5)
            coVerify { shoppingItemDao.insert(shoppingItem5) }
        }
    }

    @Test
    fun clearShoppingItems() {
        runBlocking {
            repository.clear()
            coVerify { shoppingItemDao.clear() }
        }
    }

    @Test
    fun deleteShoppingItem() {
        runBlocking {
            repository.delete(shoppingItem1Id)
            coVerify { shoppingItemDao.delete(shoppingItem1Id) }
        }
    }
}
