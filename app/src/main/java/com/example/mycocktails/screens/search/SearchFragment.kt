package com.example.mycocktails.screens.search


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
        binding.categoryRecycleView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val application = requireNotNull(this.activity).application

        val dataSource = CocktailDatabase.getInstance(application).categoryDao
        val viewModelFactory =
            CategoryViewModelFactory(dataSource, application)
        val SearchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        binding.searchViewModel = SearchViewModel
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)


        binding.SearchBtn.setOnClickListener{
            view: View ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToCocktailFragment(
                    null,
                    SearchBar.text.toString()
                )
            )
        }
/*
        binding.searchBar.setOnClickListener{
            view: View ->

            viewModel.onNavigated()//anders kan je niet terug

        }*/
//        var args = CocktailFragmentArgs.fromBundle(arguments!!)

/*

//val whatever: LiveData<String> = Transformation.map(_word) {it.toString()}

        var searchBar = binding.searchBar; //werkt dit?? lol
        searchBar.setOnSearchClickListener{
            parentFragment!!.findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToCocktailFragment(null, searchBar.query as String)
            )
        }
*/
        /*
          cocktailBtn.setOnClickListener{ navController.navigate(
                cocktailFragmentDirections.actionCocktailFragmentToRecipeFragment(
                    cockatail.name
                )
            )}
        */

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

        SearchViewModel.category.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        //maakt instantie van SearchViewModel --> associatie --> heroproepen --> zelfde ViewModel

        binding.categoryRecycleView.layoutManager = GridLayoutManager(context, 2)
        binding.categoryRecycleView.adapter = adapter

        return binding.root
    }
}
