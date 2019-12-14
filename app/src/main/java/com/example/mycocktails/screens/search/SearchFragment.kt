package com.example.mycocktails.screens.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mycocktails.R
import com.example.mycocktails.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_search, container, false)

        binding.searchViewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.SearchFragmentButtonStartSearch.setOnClickListener {
            view: View ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToCocktailFragment(
                    null,
                    SearchFragment_TextInput_SearchBar.text.toString()
                )
            )
        }

        val adapter =
            CategoryAdapter(CategoryListener { name ->
                viewModel.onCategoryClicked(name)
            })

        viewModel.navigateToCategory.observe(this, Observer { name ->
            name?.let {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToCocktailFragment(
                        name,
                        null
                    )
                )
                viewModel.onNavigated()
            }
        })

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.SearchFragmentRecycleViewCategoryRecycleView.layoutManager = GridLayoutManager(context, 3)
        } else {
            binding.SearchFragmentRecycleViewCategoryRecycleView.layoutManager = GridLayoutManager(context, 2)
        }

        // binding.SearchFragmentRecycleViewCategoryRecycleView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.SearchFragmentRecycleViewCategoryRecycleView.adapter = adapter

        return binding.root
    }
}
