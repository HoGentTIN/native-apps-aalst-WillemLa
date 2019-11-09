package com.example.mycocktails.screens.cocktail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktails.databinding.ListLayoutBinding
import com.example.mycocktails.domain.Cocktail

//Listadapter: Kijkt wat er veranderd is bij update (docktaildiffcallback) + fielddata niet meer definiëren + geen itemCount
class CocktailAdapter(val clickListener: CocktailListener) : ListAdapter<Cocktail, CocktailAdapter.ViewHolder>(
    CocktailDiffCallback()
){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail: Cocktail = getItem(position)
        holder.bind(cocktail, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }


    class ViewHolder(val binding: ListLayoutBinding, val navController: NavController) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            cocktail: Cocktail,
            clickListener: CocktailListener
        ) {

            binding.cocktail = cocktail
            binding.executePendingBindings()
            binding.clickListener = clickListener



/*
            val res = itemView.context.resources
            cocktailBtn.text = cockatail.name

            when (position % 4) {
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

            }*/
/*
            cocktailBtn.setOnClickListener{ navController.navigate(
                CocktailFragmentDirections.actionCocktailFragmentToRecipeFragment(
                    cockatail.name
                )
            )}*/

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListLayoutBinding.inflate(layoutInflater, parent, false)
                val navController = parent.findNavController()
                return ViewHolder(
                    binding,
                    navController
                );
            }
        }
    }
}
class CocktailDiffCallback : DiffUtil.ItemCallback<Cocktail>() {
    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem.cocktailId == newItem.cocktailId
    }

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem == newItem
    }
}

class CocktailListener(val clickListener: (cocktailId: Long) -> Unit){
    fun onClick(cocktail: Cocktail) = clickListener(cocktail.cocktailId)
}