package com.example.mycocktails.screens.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mycocktails.R
import com.example.mycocktails.databinding.FragmentRecipeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class RecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModel<RecipeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_recipe, container, false)

        val args = RecipeFragmentArgs.fromBundle(arguments!!)

        viewModel.initCocktail(args.cocktailId)

        viewModel.cocktail.observe(this, Observer {
                binding.cocktail = it
        }
        )

        binding.setLifecycleOwner(this)

        return binding.root
    }
}
