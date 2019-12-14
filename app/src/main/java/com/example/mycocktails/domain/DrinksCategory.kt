package com.example.mycocktails.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "drinksCategory_table")
data class DrinksCategory(

    @Json(name = "drinks")
    var categories: List<Category>? = ArrayList(),

    @PrimaryKey(autoGenerate = true)
    var drinksId: Long = 0L
)
