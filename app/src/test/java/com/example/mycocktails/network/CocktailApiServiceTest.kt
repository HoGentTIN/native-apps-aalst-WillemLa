package com.example.mycocktails.network

import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.domain.Drinks
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CocktailApiServiceTest {

    //region Testing: mockk
    private val cocktailDao: CocktailDao = mockk()
    private val categoryDao: CategoryDao = mockk()
    private lateinit var repository: CocktailRepository

    private val drink1: Drinks = mockk()
    private val drink2: Drinks = mockk()
    private val drink: Drinks = mockk()
    private val drinkWhereDrinksReturnsNull: Drinks = mockk()

    private val cocktailAPiService: CocktailApiService = mockk()
    private val connectivityManager: ConnectivityManager = mockk()

    private val testCocktail1: Cocktail = mockk()
    private val testCocktail2: Cocktail = mockk()
    private val testCocktail3: Cocktail = mockk()
    private val testCocktail4: Cocktail = mockk()

    private val testId1: Long = 1L
    private val testId2: Long = 2L

    private val networkInfo: NetworkInfo = mockk()
    //endregion

    @Before
    fun setUp() {
        coEvery { cocktailDao.getByCategory(any()) } returns listOf(testCocktail3, testCocktail4)
        coEvery { cocktailDao.getByCocktailName(any()) } returns listOf(mockk(), mockk())
        coEvery { cocktailDao.getAll() } returns listOf(mockk(), mockk())
        coEvery { cocktailDao.insert(any()) } returns Unit
        coEvery { categoryDao.get(any()) } returns mockk()

        coEvery { cocktailAPiService.getCocktails(any()) } returns drink
        coEvery { cocktailAPiService.getCocktailsById("1") } returns drink1
        coEvery { cocktailAPiService.getCocktailsById("2") } returns drink2
        coEvery { cocktailAPiService.getCocktailsByName("") } returns drink
        coEvery { cocktailAPiService.getCocktailsByName("returnNull") } returns drinkWhereDrinksReturnsNull
        coEvery { testCocktail1.cocktailId } returns testId1
        coEvery { testCocktail2.cocktailId } returns testId2
        coEvery { drink.drinks } returns arrayListOf(testCocktail1, testCocktail2)
        coEvery { drink1.drinks } returns arrayListOf(testCocktail1)
        coEvery { drink2.drinks } returns arrayListOf(testCocktail2)
        coEvery { drinkWhereDrinksReturnsNull.drinks } returns null
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        repository = CocktailRepository(cocktailDao, cocktailAPiService, connectivityManager)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun cocktailRepository_getCocktailsByCategory_online_returnsApiAndDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns true
        // Act
        runBlockingTest {
            val cocktails = repository.getAllCocktailsByCategory("Ordinary Drink")
            // Assert
            coVerify { cocktailAPiService.getCocktails(any()) }
            coVerify { cocktailAPiService.getCocktailsById(any()) }
            assertEquals(4, cocktails.size)
        }
    }

    @Test
    fun cocktailRepository_getCocktailsByCategory_offline_returnsEverythingFromDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns false
        // Act
        runBlockingTest {
            val cocktails = repository.getAllCocktailsByCategory("")
            // Assert
            coVerify { cocktailAPiService.getCocktails(any()) wasNot Called } // Niet gebruikt
            coVerify { cocktailAPiService.getCocktailsById(any()) wasNot Called } // Niet gebruikt
            assertEquals(2, cocktails.size)
        }
    }

    @Test
    fun cocktailRepository_getCocktailsByName_online_returnsApiAndDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns true
        // Act
        runBlockingTest {
            val cocktails = repository.getAllCocktailsByName("")
            // Assert
            coVerify { cocktailAPiService.getCocktailsByName(any()) }
            coVerify { cocktailAPiService.getCocktailsById(any()) }
            assertEquals(4, cocktails.size)
        }
    }

    @Test
    fun cocktailRepository_getCocktailsByName_online_NameDoesntExists_DrinksReturnNUll_returnsEverythingFromDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns true
        // Act
        runBlockingTest {
            val cocktails = repository.getAllCocktailsByName("returnNull")
            // Assert
            coVerify { cocktailAPiService.getCocktailsByName(any())!! wasNot Called } // Niet gebruikt
            coVerify { cocktailAPiService.getCocktailsById(any()) wasNot Called } // Niet gebruikt
            assertEquals(2, cocktails.size)
        }
    }

    @Test
    fun cocktailRepository_getCocktailsByName_offline_returnsEverythingFromDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns false
        // Act
        runBlockingTest {
            val cocktails = repository.getAllCocktailsByName("")
            // Assert
            coVerify { cocktailAPiService.getCocktailsByName(any())!! wasNot Called } // Niet gebruikt
            coVerify { cocktailAPiService.getCocktailsById(any()) wasNot Called } // Niet gebruikt
            assertEquals(2, cocktails.size)
        }
    }
}
