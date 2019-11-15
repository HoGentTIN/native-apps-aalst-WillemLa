package com.example.mycocktails.screens.search


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycocktails.R
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.databinding.FragmentSearchBinding
import com.example.mycocktails.domain.CategoryRepository
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.network.CocktailApi
import com.example.mycocktails.screens.cocktail.CocktailViewModelFactory
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //view inflate + bindind class instantie
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_search, container, false)
        binding.SearchFragmentRecycleViewCategoryRecycleView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val application = requireNotNull(this.activity).application

        val cocktailApiService = CocktailApi.retrofitService
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val dataSource = CocktailDatabase.getInstance(application).categoryDao
        val viewModelFactory =
            CategoryViewModelFactory(
                CategoryRepository(dataSource, cocktailApiService, connectivityManager), application)
        val SearchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        binding.searchViewModel = SearchViewModel
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        binding.SearchFragmentButtonStartSearch.setOnClickListener{
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
                SearchViewModel.onCategoryClicked(name)
            })

        SearchViewModel.navigateToCategory.observe(this, Observer { name ->
            name?.let{
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToCocktailFragment(
                        name,
                        null
                    )
                )
                viewModel.onNavigated()//anders kan je niet terug
            }
        })

        SearchViewModel.categories.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })



        //maakt instantie van SearchViewModel --> associatie --> heroproepen --> zelfde ViewModel
        binding.SearchFragmentRecycleViewCategoryRecycleView.layoutManager = GridLayoutManager(context, 2)
        binding.SearchFragmentRecycleViewCategoryRecycleView.adapter = adapter

        return binding.root
    }
}
