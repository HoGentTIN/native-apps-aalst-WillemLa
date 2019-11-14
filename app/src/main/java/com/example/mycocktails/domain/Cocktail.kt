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

    @Json(name= "strInstructions")
    var instructions: String = "",

    @Json(name= "strIngredient1")
    var ingredient1: String?,
    @Json(name= "strIngredient2")
    var ingredient2: String? ,
    @Json(name= "strIngredient3")
    var ingredient3: String? ,
    @Json(name= "strIngredient4")
    var ingredient4: String? ,
    @Json(name= "strIngredient5")
    var ingredient5: String? ,
    @Json(name= "strIngredient6")
    var ingredient6: String? ,
    @Json(name= "strIngredient7")
    var ingredient7: String? ,
    @Json(name= "strIngredient8")
    var ingredient8: String? ,
    @Json(name= "strIngredient9")
    var ingredient9: String? ,
    @Json(name= "strIngredient10")
    var ingredient10: String?,
    @Json(name= "strIngredient11")
    var ingredient11: String?,
    @Json(name= "strIngredient12")
    var ingredient12: String?,
    @Json(name= "strIngredient13")
    var ingredient13: String? ,
    @Json(name= "strIngredient14")
    var ingredient14: String? ,
    @Json(name= "strIngredient15")
    var ingredient15: String? ,

    @Json(name= "strMeasure1")
    var ingredientAmount1: String? ,
    @Json(name= "strMeasure2")
    var ingredientAmount2: String? ,
    @Json(name= "strMeasure3")
    var ingredientAmount3: String? ,
    @Json(name= "strMeasure4")
    var ingredientAmount4: String? ,
    @Json(name= "strMeasure5")
    var ingredientAmount5: String? ,
    @Json(name= "strMeasure6")
    var ingredientAmount6: String? ,
    @Json(name= "strMeasure7")
    var ingredientAmount7: String? ,
    @Json(name= "strMeasure8")
    var ingredientAmount8: String? ,
    @Json(name= "strMeasure9")
    var ingredientAmount9: String? ,
    @Json(name= "strMeasure10")
    var ingredientAmount10: String?,
    @Json(name= "strMeasure11")
    var ingredientAmount11: String?,
    @Json(name= "strMeasure12")
    var ingredientAmount12: String?,
    @Json(name= "strMeasure13")
    var ingredientAmount13: String?,
    @Json(name= "strMeasure14")
    var ingredientAmount14: String?,
    @Json(name= "strMeasure15")
    var ingredientAmount15: String?,

    @PrimaryKey
    @Json(name= "idDrink")
    var cocktailId: Long = 0L
)
