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
        // Inflate layout voor fragment
        val binding: FragmentCreateCocktailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_cocktail, container, false
        )

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
            binding.CreateCocktailFragmentTextInputIngredientAmount2,
            binding.CreateCocktailFragmentTextInputIngredient2,
            binding.CreateCocktailFragmentTextInputIngredientAmount3,
            binding.CreateCocktailFragmentTextInputIngredient3,
            binding.CreateCocktailFragmentTextInputIngredientAmount4,
            binding.CreateCocktailFragmentTextInputIngredient4,
            binding.CreateCocktailFragmentTextInputIngredientAmount5,
            binding.CreateCocktailFragmentTextInputIngredient5,
            binding.CreateCocktailFragmentTextInputIngredientAmount6,
            binding.CreateCocktailFragmentTextInputIngredient6,
            binding.CreateCocktailFragmentTextInputIngredientAmount7,
            binding.CreateCocktailFragmentTextInputIngredient7,
            binding.CreateCocktailFragmentTextInputIngredientAmount8,
            binding.CreateCocktailFragmentTextInputIngredient8,
            binding.CreateCocktailFragmentTextInputIngredientAmount9,
            binding.CreateCocktailFragmentTextInputIngredient9,
            binding.CreateCocktailFragmentTextInputIngredientAmount10,
            binding.CreateCocktailFragmentTextInputIngredient10,
            binding.CreateCocktailFragmentTextInputIngredientAmount11,
            binding.CreateCocktailFragmentTextInputIngredient11,
            binding.CreateCocktailFragmentTextInputIngredientAmount12,
            binding.CreateCocktailFragmentTextInputIngredient12,
            binding.CreateCocktailFragmentTextInputIngredientAmount13,
            binding.CreateCocktailFragmentTextInputIngredient13,
            binding.CreateCocktailFragmentTextInputIngredientAmount14,
            binding.CreateCocktailFragmentTextInputIngredient14,
            binding.CreateCocktailFragmentTextInputIngredientAmount15,
            binding.CreateCocktailFragmentTextInputIngredient15
        )

        ExtraTextInputEditTextFields.forEach { textField ->
            textField.visibility = View.GONE
        }


        binding.CreateCocktailFragmentButtonAddIngredient.setOnClickListener {
                for (x in 0 until ExtraTextInputEditTextFields.size - 1)
                    if (ExtraTextInputEditTextFields[x].visibility == View.GONE) {
                        ExtraTextInputEditTextFields[x].visibility = View.VISIBLE
                        ExtraTextInputEditTextFields[x + 1].visibility = View.VISIBLE
                        break;
                    }
        }


        binding.CreateCocktailFragmentButtonAddCocktail.setOnClickListener {
            var c = Cocktail(
                binding.CreateCocktailFragmentTextViewCocktailName.text.toString(),
                binding.CreateCocktailFragmentSpinnerCocktailCategory.selectedItem.toString(),
                binding.CreateCocktailFragmentTextInputCocktailInstructions.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient1.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient2.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient3.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient4.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient5.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient6.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient7.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient8.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient9.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient10.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient11.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient12.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient13.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient14.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredient15.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount1.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount2.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount3.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount4.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount5.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount6.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount7.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount9.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount10.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount11.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount11.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount12.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount13.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount14.text.toString(),
                binding.CreateCocktailFragmentTextInputIngredientAmount15.text.toString()
            )

            viewModel.addCocktail(c)


            binding.CreateCocktailFragmentTextInputIngredientAmount2.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient2.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount3.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient3.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount4.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient4.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount5.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient5.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount6.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient6.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount7.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient7.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount8.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient8.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount9.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient9.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount10.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient10.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount11.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient11.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount12.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient12.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount13.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient13.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount14.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient14.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredientAmount15.text!!.clear()
            binding.CreateCocktailFragmentTextInputIngredient15.text!!.clear()

            binding.CreateCocktailFragmentTextViewCocktailName.text!!.clear()
            binding.CreateCocktailFragmentTextInputCocktailInstructions.text!!.clear()

            binding.CreateCocktailFragmentTextInputIngredientAmount2.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient2.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount3.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient3.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount4.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient4.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount5.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient5.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount6.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient6.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount7.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient7.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount8.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient8.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount9.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient9.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount10.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient10.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount11.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient11.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount12.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient12.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount13.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient13.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount14.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient14.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredientAmount15.visibility = View.GONE
            binding.CreateCocktailFragmentTextInputIngredient15.visibility = View.GONE

            // TODO array?

        }



        return binding.root

    }
}
