package com.example.mycocktails

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.network.CocktailApiService
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import io.mockk.*
import kotlinx.coroutines.test.runBlockingTest


class ApiTest {


    private val cocktailDao: CocktailDao= mockk()
    private val categoryDao: CategoryDao = mockk()

    private lateinit var testCocktail: Cocktail
    private lateinit var testCategory: Category

    private val cocktailAPiService: CocktailApiService = mockk()
    private val connectivityManager: ConnectivityManager = mockk()
    private val networkInfo: NetworkInfo = mockk()
    private lateinit var repository: CocktailRepository

    @Before
    fun setUp() {
        coEvery { cocktailDao.getByCategory(any()) } returns listOf(mockk(), mockk())
        coEvery { cocktailDao.getByCocktailName(any()) } returns mockk()
        coEvery { cocktailDao.insert(any()) } returns Unit
        coEvery { categoryDao.get(any()) } returns mockk()

        coEvery { cocktailAPiService.getCocktails(any()) } returns mockk()
        coEvery { cocktailAPiService.getCocktailsById(any()) } returns mockk()
        coEvery { cocktailAPiService.getCocktailsByName(any()) } returns mockk()

        every { connectivityManager.activeNetworkInfo } returns networkInfo

        repository = CocktailRepository(cocktailDao, cocktailAPiService, connectivityManager)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun wordRepo_getWords_online_returnsApiAndDatabase() {
        //Arrange
        every { networkInfo.isConnected } returns true
        //Act
        runBlockingTest {
            val words = repository.getAllCocktailsByCategory("Ordinary Drink")
            //Assert
           // coVerify { wordDao.getAllWords() }
            //coVerify { wordAPiService.getWords() }
            assertEquals(4, words.size)
        }
    }
}