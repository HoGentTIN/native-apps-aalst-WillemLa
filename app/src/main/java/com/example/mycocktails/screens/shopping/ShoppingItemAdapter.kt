package com.example.mycocktails.screens.shopping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktails.databinding.ShoppingitemLayoutBinding
import com.example.mycocktails.domain.ShoppingItem

class ShoppingItemAdapter(val clickListener: ShoppingItemListener) : ListAdapter<ShoppingItem, ShoppingItemAdapter.ViewHolder>
    (
    ShoppingDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    class ViewHolder(val binding: ShoppingitemLayoutBinding, val navController: NavController) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            shoppingItem: ShoppingItem,
            shoppingItemListener: ShoppingItemListener
        ) {
            binding.shoppingItem = shoppingItem
            binding.executePendingBindings()
            binding.clickListener = shoppingItemListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ShoppingitemLayoutBinding.inflate(layoutInflater, parent, false)
                val navController = parent.findNavController()
                return ViewHolder(
                    binding,
                    navController
                )
            }
        }
    }
}

class ShoppingDiffCallback : DiffUtil.ItemCallback<ShoppingItem>() {
    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem.shoppingItemId == newItem.shoppingItemId
    }
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem == newItem
    }
}

class ShoppingItemListener(val clickListener: (shoppingItemId: Long) -> Unit) {
    fun onClick(shoppingItem: ShoppingItem) = clickListener(shoppingItem.shoppingItemId)
}
