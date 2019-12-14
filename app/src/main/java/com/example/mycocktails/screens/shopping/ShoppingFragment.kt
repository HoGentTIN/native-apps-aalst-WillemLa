package com.example.mycocktails.screens.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycocktails.databinding.FragmentShoppingBinding
import com.example.mycocktails.domain.ShoppingItem
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class ShoppingFragment : Fragment() {

    private val viewModel: ShoppingItemViewModel by viewModel<ShoppingItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentShoppingBinding = FragmentShoppingBinding.inflate(inflater)

        binding.shoppingViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter =
            ShoppingItemAdapter(ShoppingItemListener { shoppingItemId ->
                viewModel.onShoppingItemClicked(shoppingItemId)
            })

        binding.ShoppingFragmentRecyclerView.adapter = adapter
        binding.ShoppingFragmentRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.deleteShoppingItem.observe(this, Observer { id ->
            id?.let {
                val builder = AlertDialog.Builder(context!!)
                builder.setMessage("Do you want to delete this item?")
                builder.setPositiveButton("Yes") { dialog, which ->
                    viewModel.delete(id)
                    viewModel.onNavigated() // TODO nodig?
                    Toast.makeText(context, "The item is deleted.", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Cancel") { dialog, which ->
                    Toast.makeText(context, "The item was not deleted.", Toast.LENGTH_SHORT).show()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        })

        binding.ShoppingFragmentClearListButton.setOnClickListener {
                view: View ->
            val builder = AlertDialog.Builder(context!!)
            builder.setMessage("Clear list?")
            builder.setPositiveButton("Yes") { dialog, which ->
                viewModel.clear()
                Toast.makeText(context, "The list was cleared.", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(context, "The list was not cleared.", Toast.LENGTH_SHORT).show()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        binding.ShoppingFragmentAddItemButton.setOnClickListener {
                view: View ->

            if (binding.ShoppingFragmentTextInputSearchBar.text.toString().isEmpty()) {
                Toast.makeText(context, "Please fill in the required field.", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(ShoppingItem(binding.ShoppingFragmentTextInputSearchBar.text.toString()))
                binding.ShoppingFragmentTextInputSearchBar.text!!.clear()
            }
        }

        viewModel.shoppingItems.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }
}
