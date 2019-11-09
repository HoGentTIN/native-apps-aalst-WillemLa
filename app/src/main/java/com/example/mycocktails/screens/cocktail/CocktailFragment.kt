package com.example.mycocktails.screens.cocktail


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycocktails.R
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.databinding.FragmentCocktailBinding

/**
 * A simple [Fragment] subclass.
 */
class CocktailFragment : Fragment() {

    private lateinit var viewModel: CocktailViewModel

    @SuppressLint("WrongConstant") //nog oplossen (LinearLayout.VERITCAL)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCocktailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_cocktail, container, false)
        binding.RecyclerViewId.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)


        var args = CocktailFragmentArgs.fromBundle(arguments!!)


        val application = requireNotNull(this.activity).application
        val dataSource = CocktailDatabase.getInstance(application).cocktailDao
        val viewModelFactory = CocktailViewModelFactory(
            dataSource,
            application,
            args.categoryName,
            args.cocktailName
        )
        val CocktailViewModel = ViewModelProviders.of(this, viewModelFactory).get(CocktailViewModel::class.java)
        binding.cocktailViewModel = CocktailViewModel
        binding.setLifecycleOwner(this)


        viewModel = ViewModelProviders.of(this).get(com.example.mycocktails.screens.cocktail.CocktailViewModel::class.java)

        /*CocktailViewModel.cocktails.observe(this, Observer {
                cocktail -> cocktail?.let{
            viewModel.setCocktails(args.categoryName, args.cocktailName)
        }
        })*/

        //DEZE WORDT NOG GEFIXT//viewModel.setCocktails(args.categoryName, args.cocktailName)

        val adapter =
            CocktailAdapter(CocktailListener { cocktailId ->
                CocktailViewModel.onCocktailClicked(cocktailId)
            })

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

        binding.RecyclerViewId.adapter = adapter

        CocktailViewModel.cocktails.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        /*
        if (args.categoryName != null){
            binding.RecyclerViewId.adapter =
                CocktailAdapter(viewModel.cocktails.value!!.filter { c -> c.category == args.categoryName })
        }else{
            binding.RecyclerViewId.adapter =
                CocktailAdapter(viewModel.cocktails.value!!.filter { c ->args.cocktailName!!.contains(c.name)})
        }
        */

        return binding.root
    }

}
