package com.example.mycocktails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktails.SearchFragmentDirections

import com.example.mycocktails.databinding.CategorieLayoutBinding
import com.example.mycocktails.databinding.ListLayoutBinding

class CategoryAdapter( val clickListener: CategoryListener): ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = getItem(position)
        holder.bind(category, position, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    class ViewHolder(val binding: CategorieLayoutBinding, val navController: NavController) : RecyclerView.ViewHolder(binding.root){

        /*
        val textViewName = binding.CategoryBtnId//  itemView.findViewById<TextView>(R.id.CategoryBtnId)
        val image = binding.CategoryImgId// itemView.findViewById<ImageButton>(R.id.CategoryImgId)
        var viewModel = viewModel */

        fun bind(
            category: Category,
            position: Int,
            clickListener: CategoryListener
        ) {

            binding.category = category
            binding.executePendingBindings()
            binding.clickListener = clickListener

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategorieLayoutBinding.inflate(layoutInflater, parent, false)
                val navController = parent.findNavController()
                return ViewHolder(
                    binding,
                    navController
                );
            }
        }
 }



/*
        fun bind(category: Category, position: Int) {

            image.setImageResource(viewModel.getImageResource(category.name))

            textViewName.text = category.name
            textViewName.setOnClickListener{ navController.navigate(
                SearchFragmentDirections.actionSearchFragmentToCocktailFragment(
                    category.name,
                    null
                )
            )}
            image.setOnClickListener{ navController.navigate(
                SearchFragmentDirections.actionSearchFragmentToCocktailFragment(
                    category.name,
                    null
                )
            )}

        }
*/
    /*
        companion object{
            fun from(parent: ViewGroup, viewModel: SearchViewModel): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategorieLayoutBinding.inflate(layoutInflater, parent, false) //fix
                val navController = parent.findNavController();
                return ViewHolder(
                    binding,
                    navController,
                    viewModel
                );
            }
        }
*/
    }

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}

class CategoryListener(val clickListener: (categoryName: String) -> Unit) {
    fun onClick(category: Category) = clickListener(category.name)
}

