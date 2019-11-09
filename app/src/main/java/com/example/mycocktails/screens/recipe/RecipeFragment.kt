package com.example.mycocktails.screens.recipe


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mycocktails.R
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.databinding.FragmentRecipeBinding

/**
 * A simple [Fragment] subclass.
 */
class RecipeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRecipeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_recipe, container, false)



        var args = RecipeFragmentArgs.fromBundle(arguments!!)

//        binding.CocktailTitel.text = args.cocktailId.toString()
        val application = requireNotNull(this.activity).application
        val dataSource = CocktailDatabase.getInstance(application).cocktailDao
        val viewModelFactory =
            RecipeViewModelFactory(
                args.cocktailId,
                dataSource
            )
        val RecipeViewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeViewModel::class.java)
        binding.recipeViewModel = RecipeViewModel
        binding.setLifecycleOwner(this)


        return binding.root;
    }


}
