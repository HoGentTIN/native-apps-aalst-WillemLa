package com.example.mycocktails.screens.create


import android.content.Context
import android.inputmethodservice.ExtractEditText
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TableRow
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mycocktails.R
import com.example.mycocktails.database.CocktailDatabase
import com.example.mycocktails.databinding.FragmentCreateCocktailBinding
import com.example.mycocktails.domain.Cocktail
import com.example.mycocktails.domain.CocktailRepository
import com.example.mycocktails.network.CocktailApi
import com.example.mycocktails.screens.cocktail.CocktailViewModel
import com.example.mycocktails.screens.cocktail.CocktailViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_create_cocktail.view.*
import kotlin.math.E

/**
 * A simple [Fragment] subclass.
 */
class CreateCocktailFragment : Fragment() {

    private lateinit var viewModel: CreateCocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateCocktailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_cocktail, container, false
        )

        var table = binding.TableLayoutId

        val cocktailApiService = CocktailApi.retrofitService
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val application = requireNotNull(this.activity).application
        val dataSource = CocktailDatabase.getInstance(application).cocktailDao
        val viewModelFactory = CreateCocktailViewModelFactory(
            CocktailRepository(dataSource, cocktailApiService, connectivityManager),
            application
        )
        val CreateCocktailViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CreateCocktailViewModel::class.java)

        binding.setLifecycleOwner(this)

        viewModel = CreateCocktailViewModel


        var ExtraTextInputEditTextFields = arrayListOf<TextInputEditText>(

            binding.CreateCocktailFragment_TextInput_IngredientAmount2,
            binding.Ingredient2,
            binding.CreateCocktailFragment_TextInput_IngredientAmount3,
            binding.Ingredient3,
            binding.CreateCocktailFragment_TextInput_IngredientAmount4,
            binding.Ingredient4,
            binding.CreateCocktailFragment_TextInput_IngredientAmount5,
            binding.Ingredient5,
            binding.CreateCocktailFragment_TextInput_IngredientAmount6,
            binding.Ingredient6,
            binding.CreateCocktailFragment_TextInput_IngredientAmount7,
            binding.Ingredient7,
            binding.CreateCocktailFragment_TextInput_IngredientAmount8,
            binding.Ingredient8,
            binding.CreateCocktailFragment_TextInput_IngredientAmount9,
            binding.Ingredient9,
            binding.CreateCocktailFragment_TextInput_IngredientAmount10,
            binding.Ingredient10,
            binding.CreateCocktailFragment_TextInput_IngredientAmount11,
            binding.Ingredient11,
            binding.CreateCocktailFragment_TextInput_IngredientAmount12,
            binding.Ingredient12,
            binding.CreateCocktailFragment_TextInput_IngredientAmount13,
            binding.Ingredient13,
            binding.CreateCocktailFragment_TextInput_IngredientAmount14,
            binding.Ingredient14,
            binding.CreateCocktailFragment_TextInput_IngredientAmount15,
            binding.Ingredient15
        )

        ExtraTextInputEditTextFields.forEach { textField ->
            textField.visibility = View.GONE
        }


        binding.addIngredientBtn.setOnClickListener {
                for (x in 0 until ExtraTextInputEditTextFields.size - 1)
                    if (ExtraTextInputEditTextFields[x].visibility == View.GONE) {
                        ExtraTextInputEditTextFields[x].visibility = View.VISIBLE
                        ExtraTextInputEditTextFields[x + 1].visibility = View.VISIBLE
                        break;
                    }
        }


        binding.addCocktail.setOnClickListener {
            var c = Cocktail(
                binding.cocktailNaam.text.toString(),
                binding.cocktailCategory.selectedItem.toString(),
                binding.cocktailWerkwijze.text.toString(),
                binding.Ingredient1.text.toString(),
                binding.Ingredient2.text.toString(),
                binding.Ingredient3.text.toString(),
                binding.Ingredient4.text.toString(),
                binding.Ingredient5.text.toString(),
                binding.Ingredient6.text.toString(),
                binding.Ingredient7.text.toString(),
                binding.Ingredient8.text.toString(),
                binding.Ingredient9.text.toString(),
                binding.Ingredient10.text.toString(),
                binding.Ingredient11.text.toString(),
                binding.Ingredient12.text.toString(),
                binding.Ingredient13.text.toString(),
                binding.Ingredient14.text.toString(),
                binding.Ingredient15.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount1.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount2.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount3.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount4.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount5.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount6.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount7.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount9.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount10.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount11.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount11.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount12.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount13.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount14.text.toString(),
                binding.CreateCocktailFragment_TextInput_IngredientAmount15.text.toString()
                )


            /*
                view!!.findViewWithTag<EditText>("Ingredient2").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient3").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient4").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient5").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient6").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient7").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient8").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient9").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient10").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient11").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient12").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient13").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient14").text.toString(),
                view!!.findViewWithTag<EditText>("Ingredient15").text.toString(),

                binding.CreateCocktailFragment_TextInput_IngredientAmount1.text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount2").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount3").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount4").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount5").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount6").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount7").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount8").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount9").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount10").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount11").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount12").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount13").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount14").text.toString(),
                view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount15").text.toString()
            )

                    var c = Cocktail()
                    c.name = binding.cocktailNaam.text.toString()
                    c.category = binding.cocktailCategory.selectedItem.toString()
                    c.instructions = binding.cocktailWerkwijze.text.toString()

                    c.ingredient1 = binding.Ingredient1.text.toString()
                    c.ingredient2 = view!!.findViewWithTag<EditText>("Ingredient2").text.toString()
                    c.ingredient3 = view!!.findViewWithTag<EditText>("Ingredient3").text.toString()
                    c.ingredient4 = view!!.findViewWithTag<EditText>("Ingredient4").text.toString()
                    c.ingredient5 = view!!.findViewWithTag<EditText>("Ingredient5").text.toString()
                    c.ingredient6 = view!!.findViewWithTag<EditText>("Ingredient6").text.toString()
                    c.ingredient7 = view!!.findViewWithTag<EditText>("Ingredient7").text.toString()
                    c.ingredient8 = view!!.findViewWithTag<EditText>("Ingredient8").text.toString()
                    c.ingredient9 = view!!.findViewWithTag<EditText>("Ingredient9").text.toString()
                    c.ingredient10 = view!!.findViewWithTag<EditText>("Ingredient10").text.toString()
                    c.ingredient11 = view!!.findViewWithTag<EditText>("Ingredient11").text.toString()
                    c.ingredient12 = view!!.findViewWithTag<EditText>("Ingredient12").text.toString()
                    c.ingredient13 = view!!.findViewWithTag<EditText>("Ingredient13").text.toString()
                    c.ingredient14 = view!!.findViewWithTag<EditText>("Ingredient14").text.toString()
                    c.ingredient15 = view!!.findViewWithTag<EditText>("Ingredient15").text.toString()

                    c.ingredientAmount1 = binding.CreateCocktailFragment_TextInput_IngredientAmount1.text.toString()
                    c.ingredientAmount2 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount2").text.toString()
                    c.ingredientAmount3 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount3").text.toString()
                    c.ingredientAmount4 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount4").text.toString()
                    c.ingredientAmount5 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount5").text.toString()
                    c.ingredientAmount6 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount6").text.toString()
                    c.ingredientAmount7 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount7").text.toString()
                    c.ingredientAmount8 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount8").text.toString()
                    c.ingredientAmount9 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount9").text.toString()
                    c.ingredientAmount10 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount10").text.toString()
                    c.ingredientAmount11 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount11").text.toString()
                    c.ingredientAmount12 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount12").text.toString()
                    c.ingredientAmount13 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount13").text.toString()
                    c.ingredientAmount14 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount14").text.toString()
                    c.ingredientAmount15 = view!!.findViewWithTag<EditText>("CreateCocktailFragment_TextInput_IngredientAmount15").text.toString()
                    */
                    viewModel.addCocktail(c)

            binding.CreateCocktailFragment_TextInput_IngredientAmount1.text!!.clear()
            binding.CreateCocktailFragment_TextInput_IngredientAmount2.text!!.clear()
            binding.CreateCocktailFragment_TextInput_IngredientAmount3.text!!.clear()
            binding.cocktailNaam.text!!.clear()
            binding.Ingredient1.text!!.clear()
            binding.Ingredient2.text!!.clear()
            binding.Ingredient3.text!!.clear()
            binding.cocktailWerkwijze.text!!.clear()

            binding.cocktailNaam.visibility = View.GONE

        }



        return binding.root

    }
}
