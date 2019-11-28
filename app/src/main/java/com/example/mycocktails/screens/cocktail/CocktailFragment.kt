package com.example.mycocktails.screens.cocktail


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
import com.example.mycocktails.R
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.databinding.FragmentCocktailBinding
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.network.CocktailApi

/**
 * A simple [Fragment] subclass.
 */
class CocktailFragment : Fragment() {

    private lateinit var viewModel: CocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO aanpassen
        val binding: FragmentCocktailBinding = FragmentCocktailBinding.inflate(inflater) // DataBindingUtil.inflate(inflater, R.layout.fragment_cocktail, container, false)

        var args = CocktailFragmentArgs.fromBundle(arguments!!)

        val cocktailApiService = CocktailApi.retrofitService
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


        val dataSource = CocktailDatabase.getInstance(requireContext()).cocktailDao
        val viewModelFactory = CocktailViewModelFactory(
            CocktailRepository(dataSource, cocktailApiService, connectivityManager),
            args.categoryName,
            args.cocktailName
        )

        val CocktailViewModel = ViewModelProviders.of(this, viewModelFactory).get(CocktailViewModel::class.java)
        binding.cocktailViewModel = CocktailViewModel
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this).get(com.example.mycocktails.screens.cocktail.CocktailViewModel::class.java)

        val adapter =
            CocktailAdapter(CocktailListener { cocktailId ->
                CocktailViewModel.onCocktailClicked(cocktailId)
            })
        binding.CocktailFragmentRecyclerViewCocktailList.adapter = adapter

        //Observers
        CocktailViewModel.navigateToCocktail.observe(this, Observer { id ->
            id?.let{
                findNavController().navigate(
                    CocktailFragmentDirections.actionCocktailFragmentToRecipeFragment(
                        id
                    )
                )
                viewModel.onNavigated()//anders kan je niet terug
            }
        })

        CocktailViewModel.cocktails.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                binding.CocktailFragmentTextViewLoading.visibility = View.GONE
            }
        })

        return binding.root
    }

}
