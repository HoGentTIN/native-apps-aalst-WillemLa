package com.example.mycocktails.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.mycocktails.database.CategoryDao
import com.example.mycocktails.database.CocktailDao
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.database.ShoppingItemDao
import com.example.mycocktails.domain.CategoryRepository
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.domain.RecipeRepository
import com.example.mycocktails.domain.ShoppingItemRepository
import com.example.mycocktails.network.BASE_URL
import com.example.mycocktails.network.CocktailApiService
import com.example.mycocktails.screens.cocktail.CocktailViewModel
import com.example.mycocktails.screens.create.CreateCocktailViewModel
import com.example.mycocktails.screens.recipe.RecipeViewModel
import com.example.mycocktails.screens.search.SearchViewModel
import com.example.mycocktails.screens.shopping.ShoppingItemViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val mainModule = module {

    viewModel { CocktailViewModel(get()) }
    viewModel { CreateCocktailViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ShoppingItemViewModel(get()) }
    viewModel { RecipeViewModel(get()) }

    single { CocktailRepository(get(), get(), get()) }
    single { RecipeRepository(get()) }
    single { ShoppingItemRepository(get()) }
    single { CategoryRepository(get(), get(), get()) }

    single { provideCocktailDao(get()) }
    single { provideShopItemDao(get()) }
    single { provideCategoryDao(get()) }

    single { CocktailDatabase.getInstance(androidContext()) }

    single { provideConnectivityManager(androidContext()) }
    single { provideCocktailApiService(get()) }
    single { provideRetrofit(get()) }
    single { provideMoshi() }
}

fun provideCocktailDao(database: CocktailDatabase): CocktailDao {
    return database.cocktailDao
}

fun provideShopItemDao(database: CocktailDatabase): ShoppingItemDao {
    return database.shoppingItemDao
}

fun provideCategoryDao(database: CocktailDatabase): CategoryDao {
    return database.categoryDao
}

fun provideConnectivityManager(context: Context): ConnectivityManager {
    return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

fun provideCocktailApiService(retrofit: Retrofit): CocktailApiService {
    return retrofit.create(CocktailApiService::class.java)
}

fun provideRetrofit(moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build() as Moshi
}
