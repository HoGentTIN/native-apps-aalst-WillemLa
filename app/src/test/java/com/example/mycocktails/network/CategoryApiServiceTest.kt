package com.example.mycocktails.network

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.CategoryRepository
import com.example.mycocktails.domain.DrinksCategory
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CategoryApiServiceTest {

    private val categoryDao: CategoryDao = mockk()
    private lateinit var repository: CategoryRepository

    private val cat1: Category = mockk()
    private val cat2: Category = mockk()
    private val cat3: Category = mockk()
    private val categories: DrinksCategory = mockk()

    private val cocktailAPiService: CocktailApiService = mockk()
    private val connectivityManager: ConnectivityManager = mockk()

    private val networkInfo: NetworkInfo = mockk()

    @Before
    fun setUp() {
        coEvery { categoryDao.getAll() } returns listOf(cat2, cat3)
        coEvery { categoryDao.insert(any()) } returns Unit
        coEvery { categoryDao.clear() } returns Unit

        coEvery { cocktailAPiService.getCategories(any()) } returns categories
        coEvery { categories.categories } returns arrayListOf(cat1, cat2)

        every { connectivityManager.activeNetworkInfo } returns networkInfo
        repository = CategoryRepository(categoryDao, cocktailAPiService, connectivityManager)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun categoryRepository_getAllCategories_online_returnsCategories() {
        // Arrange
        every { networkInfo.isConnected } returns true
        // Act
        runBlockingTest {
            val categories = repository.getAllCategories()
            // Assert
            coVerify { cocktailAPiService.getCategories(any()) }
            coVerify { categoryDao.clear() }
            assertEquals(2, categories.size)
            assertTrue(categories.contains(cat1))
            assertTrue(categories.contains(cat2))
            assertFalse(categories.contains(cat3))
        }
    }

    @Test
    fun categoryRepository_getAllCategories_offline_returnsDaoCategories() {
        // Arrange
        every { networkInfo.isConnected } returns false
        // Act
        runBlockingTest {
            val categories = repository.getAllCategories()
            // Assert
            coVerify { cocktailAPiService.getCategories(any()) wasNot Called }
            assertEquals(2, categories.size)
            assertFalse(categories.contains(cat1))
            assertTrue(categories.contains(cat2))
            assertTrue(categories.contains(cat3))
        }
    }
}
