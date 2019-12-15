package com.example.mycocktails.screens.cocktail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mycocktails.databinding.FragmentCocktailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CocktailFragment : Fragment() {

    private val viewModel: CocktailViewModel by viewModel<CocktailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCocktailBinding = FragmentCocktailBinding.inflate(inflater) // DataBindingUtil.inflate(inflater, R.layout.fragment_cocktail, container, false)

        val args = CocktailFragmentArgs.fromBundle(arguments!!)

        binding.cocktailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getAllCocktailsFromDatabase(args.categoryName, args.cocktailName)

        val adapter =
            CocktailAdapter(CocktailListener { cocktailId ->
                viewModel.onCocktailClicked(cocktailId)
            })

        binding.CocktailFragmentRecyclerViewCocktailList.adapter = adapter

        viewModel.navigateToCocktail.observe(this, Observer { id ->
            id?.let {
                findNavController().navigate(
                    CocktailFragmentDirections.actionCocktailFragmentToRecipeFragment(
                        id
                    )
                )
                viewModel.onNavigated()
            }
        })

        viewModel.cocktails.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                binding.CocktailFragmentProgressbarCocktailList.visibility = View.GONE
            }
        })

        return binding.root
    }
}
