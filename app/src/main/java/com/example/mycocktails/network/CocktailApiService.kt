package com.example.mycocktails.network

import com.example.mycocktails.domain.Drinks
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://www.thecocktaildb.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface CocktailApiService {

    @GET("/api/json/v1/1/filter.php")
    suspend fun getCocktails(@Query("c") category: String?): Drinks

    @GET("/api/json/v1/1/lookup.php")
    suspend fun getCocktailsById(@Query("i") id: String?): Drinks

    @GET("/api/json/v1/1/search.php")
    suspend fun getCocktailsByName(@Query("s") name: String?): Drinks?
}

object CocktailApi {
    val retrofitService: CocktailApiService by lazy {
        retrofit.create(CocktailApiService::class.java)
    }
}