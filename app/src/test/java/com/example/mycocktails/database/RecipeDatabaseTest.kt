package com.example.mycocktails.database

import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.RecipeRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class RecipeDatabaseTest {

    private val cocktailDao: CocktailDao = mockk()
    private lateinit var repository: RecipeRepository

    private val cocktail1: Cocktail = mockk()
    private val cocktailId: Long = 0L

    @Before
    fun createDb() {
        repository = RecipeRepository(cocktailDao)
        coEvery { repository.getAllCocktails(cocktailId) } returns cocktail1
        coEvery { cocktailDao.getCocktailWithId(cocktailId) } returns cocktail1
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun insertAndGetCocktail() {
        runBlocking {
            val cocktail = repository.getAllCocktails(cocktailId)
            assertEquals(cocktail, cocktail1)
            coVerify { cocktailDao.getCocktailWithId(cocktailId) }
            coVerify { repository.getAllCocktails(cocktailId) }
        }
    }
}
