package com.example.mycocktails

import android.graphics.Color
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.mycocktails.domain.Category
import com.example.mycocktails.domain.Cocktail

@BindingAdapter("cocktailName")
fun TextView.setCocktailNaam(item: Cocktail?){
    item?.let {
        text = item.name
    }
}

@BindingAdapter("cocktailCardBackground")
fun CardView.setCardBackground(item: Cocktail?){
    item?.let {
        setCardBackgroundColor(Color.rgb(129, 149, 84))
    }
    /*
        0 -> {
            listCard.setCardBackgroundColor(Color.rgb(129, 149, 84))
            cocktailBtn.setTextColor(Color.BLACK)
        }
        1 -> {
            listCard.setCardBackgroundColor(Color.rgb(231, 215, 89))
            cocktailBtn.setTextColor(Color.BLACK)
        }
        2 -> {
            listCard.setCardBackgroundColor(Color.rgb(88, 130, 120))
            cocktailBtn.setTextColor(Color.BLACK)
        }
        3 -> {
            listCard.setCardBackgroundColor(Color.rgb(176, 66, 10))
            cocktailBtn.setTextColor(Color.BLACK)
        }
    */
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