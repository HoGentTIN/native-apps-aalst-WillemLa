package com.example.mycocktails.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "personal_category_table")
data class Category(

    @ColumnInfo(name = "categoryName")
    @Json(name = "strCategory")
    var name: String = "",

    @PrimaryKey(autoGenerate = true)
    var categoryId: Long = 0L
)
