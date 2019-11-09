package com.example.mycocktails.network

import com.example.mycocktails.domain.Cocktail
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface CocktailApiService {

    @GET("filter.php?c=Ordinary_Drink")
    suspend fun getCocktails(): List<Cocktail>
}

object CocktaiApi {
    val retrofitService: CocktailApiService by lazy {
        retrofit.create(CocktailApiService::class.java)
    }
}