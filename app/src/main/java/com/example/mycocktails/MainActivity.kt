package com.example.mycocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mycocktails.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bottomNav = binding.bottomNav
/*
        val host: NavHostFragment = supportFragmentManager
         .findFragmentById(R.id.myNavHostFragment) as NavHostFragment? ?: return
        val navController = host.navController

        NavigationUI.setupWithNavController(bottomNav, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
*/
        bottomNav.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                lateinit var selectedFragment: Fragment
                when (item.getItemId()) {
                    R.id.search -> selectedFragment =
                        SearchFragment()
                    R.id.favorites -> selectedFragment =
                        CocktailFragment()
                    R.id.extra -> selectedFragment =
                        CocktailFragment()
                }

                var fragmentManager = getSupportFragmentManager()
                fragmentManager.beginTransaction().show(selectedFragment).commit()

                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit()
                return true
            }
        })

    }
}