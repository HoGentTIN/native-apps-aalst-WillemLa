package com.example.mycocktails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal_category_table")
data class Category(
    @ColumnInfo(name = "categoryName")
    var name: String = "",

    @PrimaryKey(autoGenerate = true)
    var categoryId: Long = 0L
)
