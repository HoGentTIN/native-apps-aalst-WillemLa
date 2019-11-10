package com.example.mycocktails.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinks_table")
data class Drinks (

    var drinks: List<Cocktail> = ArrayList<Cocktail>(),

    @PrimaryKey(autoGenerate = true)
    var drinksId: Long = 0L
)