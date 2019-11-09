package com.example.mycocktails.screens.create


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mycocktails.R
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.databinding.FragmentCreateCocktailBinding
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.screens.cocktail.CocktailViewModel
import com.example.mycocktails.screens.cocktail.CocktailViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * A simple [Fragment] subclass.
 */
class CreateCocktailFragment : Fragment() {

    private lateinit var viewModel: CocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateCocktailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_create_cocktail, container, false)

        var addIngredientBtn = binding.addIngredientBtn
        var table = binding.TableLayoutId
        var addCocktail = binding.addCocktail

        val application = requireNotNull(this.activity).application
        val dataSource = CocktailDatabase.getInstance(application).cocktailDao
        val viewModelFactory =
            CocktailViewModelFactory(
                dataSource,
                application
            )





        //^^^^^^fix?^^^^




        val CocktailViewModel = ViewModelProviders.of(this, viewModelFactory).get(CocktailViewModel::class.java)
        binding.cocktailViewModel = CocktailViewModel
        binding.setLifecycleOwner(this)

        viewModel = CocktailViewModel


        binding.addIngredientBtn.setOnClickListener{
            if (table.childCount < 15){
                var tableRow = TableRow(context)
                var textInputLayout = TextInputLayout(context)
                var textInputEditText = TextInputEditText(context)

                textInputEditText.tag = "Ingrediënt" + (table.childCount+1)
                textInputEditText.hint = "Ingrediënt " + (table.childCount+1)
                textInputLayout.addView(textInputEditText)
                tableRow.addView(textInputLayout);
                table.addView(tableRow)
            }
            else {
                //snackbar
            }
        }

        binding.addCocktail.setOnClickListener{
            viewModel.addCocktail(
                Cocktail(
                    binding.cocktailNaam.text.toString(),
                    binding.cocktailCategory.selectedItem.toString()
                )
            )
        }

        return binding.root

    }


}
