package com.example.mycocktails

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.Cocktail
import java.lang.StringBuilder

@BindingAdapter("cocktailName")
fun TextView.setCocktailNaam(item: Cocktail?){
    item?.let {
        text = item.name
    }
}

@BindingAdapter("cocktailIngredients")
fun TextView.setCocktailIngredients(item: Cocktail?){
    item?.let {
        text = item.name
    }
}

@BindingAdapter("cocktailInstructions")
fun TextView.setCocktailInstructions(item: Cocktail?){
    item?.let {
        text = item.instructions
    }
}

@BindingAdapter("cocktailIngredientsFormatted")
fun TextView.formatIngredients(cocktail: Cocktail?){
    cocktail?.let {

        var sb = ""

        val IngredientStringComponents: Array<String?> = arrayOf(
            cocktail.ingredientAmount1, cocktail.ingredient1,
            cocktail.ingredientAmount2, cocktail.ingredient2,
            cocktail.ingredientAmount3 , cocktail.ingredient3 ,
            cocktail.ingredientAmount4 , cocktail.ingredient4 ,
            cocktail.ingredientAmount5 , cocktail.ingredient5 ,
            cocktail.ingredientAmount6 , cocktail.ingredient6 ,
            cocktail.ingredientAmount7 , cocktail.ingredient7 ,
            cocktail.ingredientAmount8 , cocktail.ingredient8 ,
            cocktail.ingredientAmount9 , cocktail.ingredient9 ,
            cocktail.ingredientAmount10 , cocktail.ingredient10 ,
            cocktail.ingredientAmount11 , cocktail.ingredient11 ,
            cocktail.ingredientAmount12 , cocktail.ingredient12 ,
            cocktail.ingredientAmount13 , cocktail.ingredient13 ,
            cocktail.ingredientAmount14 , cocktail.ingredient14 ,
            cocktail.ingredientAmount15 , cocktail.ingredient15
        )



           for (x in 0 until IngredientStringComponents.size-1)
           {
           if (IngredientStringComponents[x] != null  && !IngredientStringComponents[x]!!.isEmpty()  ){
               if (x % 2 != 0){
                   if (IngredientStringComponents[x-1] == null){
                       sb += "- "
                   }
                   sb += IngredientStringComponents[x] + "\n"
               }
               else{
                   sb += "- " + IngredientStringComponents[x] + " "
               }
           }
       }
        text = sb
    }
}

@BindingAdapter("cocktailCardBackground")
fun CardView.setCardBackground(item: Cocktail?){
    item?.let {
        setCardBackgroundColor(Color.rgb(129, 149, 84))
    }
}



@BindingAdapter("categoryName")
fun TextView.setCocktailCategory(item: Category?){
    item?.let {
        text = item.name
    }
}

@BindingAdapter("categoryImage")
fun ImageButton.setCategoryImage(item: Category){
    item.let {
        setImageResource(when(item.name){
            //fixen
            "Ordinary Drink" -> R.drawable.ordinary
            "Cocktail" -> R.drawable.cocktail
            "Milk / Float / Shake" ->  R.drawable.milk
            "Other/Unknown" ->  R.drawable.other
            "Cocoa" ->  R.drawable.cocoa
            "Shot" ->  R.drawable.shot
            "Coffee / Tea" ->  R.drawable.thee
            "Homemade Liquer" ->  R.drawable.self
            "Punch / Party Drink" ->  R.drawable.party
            "Beer" ->  R.drawable.bier
            "Soft Drink / Soda" ->  R.drawable.soda
            else ->  R.drawable.extra
        })
    }
}