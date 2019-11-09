package com.example.mycocktails.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

//voordeel data class: hash/toString/...
@Entity(tableName = "personal_cocktail_table")
data class Cocktail(


    @ColumnInfo(name = "cocktailName")
    @Json(name= "strDrink")
    var name: String = "",

    @ColumnInfo(name = "cocktailCategory")
    @Json(name= "strCategory")
    var category: String = "",

    @PrimaryKey(autoGenerate = true)
    var cocktailId: Long = 0L

)