package com.example.mycocktails.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.Cocktail

@Database(entities = [Cocktail::class, Category::class], version = 3, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {

    abstract val cocktailDao: CocktailDao
    abstract val categoryDao: CategoryDao

    companion object {

        @Volatile //multithreading
        private var INSTANCE: CocktailDatabase? = null

        fun getInstance(context: Context): CocktailDatabase {
            //1 executie --> 1 initialisatie
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    //databasebuilder = geef context, welke db het moet bouwen en naam
                    //geen verlies bij nieuw db-schema = migration
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CocktailDatabase::class.java,
                        "cocktail_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }



}