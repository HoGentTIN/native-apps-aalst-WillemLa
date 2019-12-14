package com.example.mycocktails.network

import com.example.mycocktails.domain.Drinks
import com.example.mycocktails.domain.DrinksCategory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.thecocktaildb.com"

interface CocktailApiService {

    @GET("/api/json/v1/1/filter.php")
    suspend fun getCocktails(@Query("c") category: String?): Drinks

    @GET("/api/json/v1/1/lookup.php")
    suspend fun getCocktailsById(@Query("i") id: String?): Drinks

    @GET("/api/json/v1/1/search.php")
    suspend fun getCocktailsByName(@Query("s") name: String?): Drinks?

    @GET("/api/json/v1/1/list.php")
    suspend fun getCategories(@Query("c") name: String?): DrinksCategory
}
