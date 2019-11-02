package com.example.mycocktails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//voordeel data class: hash/toString/...
@Entity(tableName = "personal_cocktail_table")
data class Cocktail(


    @ColumnInfo(name = "cocktailName")
    var name: String = "",

    @ColumnInfo(name = "cocktailCategory")
    var category: String = "",

    @PrimaryKey(autoGenerate = true)
    var cocktailId: Long = 0L

)