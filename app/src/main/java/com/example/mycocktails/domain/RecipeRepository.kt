package com.example.mycocktails.domain

import com.example.mycocktails.database.CocktailDao

class RecipeRepository(private val cocktailDao: CocktailDao) {
    suspend fun getAllCocktails(id: Long): Cocktail {
        return cocktailDao.getCocktailWithId(id)
    }
}
