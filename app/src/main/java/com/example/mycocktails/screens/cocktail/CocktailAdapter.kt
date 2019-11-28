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
        //getItem(position) = Cocktail
        holder.bind(getItem(position), clickListener)
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