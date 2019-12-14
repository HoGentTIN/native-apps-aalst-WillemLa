package com.example.mycocktails.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// voordeel data class: hash/toString/...
@Entity(tableName = "personal_shoppingItem_table")
data class ShoppingItem(

    @ColumnInfo(name = "cocktailName")
    var shoppingItemDescription: String = "",

    @PrimaryKey(autoGenerate = true)
    var shoppingItemId: Long = 0L
)
