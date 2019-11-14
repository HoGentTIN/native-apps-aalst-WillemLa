package com.example.mycocktails.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.mycocktails.databinding.CategorieLayoutBinding
import com.example.mycocktails.domain.Category

class CategoryAdapter( val clickListener: CategoryListener): ListAdapter<Category, CategoryAdapter.ViewHolder>(
    CategoryDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: Category = getItem(position)
        holder.bind(category, clickListener)
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

