package com.example.mycocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mycocktails.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawerLayout = binding.ActivityMainDrawerLayout

/*
        val host: NavHostFragment = supportFragmentManager
         .findFragmentById(R.id.myNavHostFragment) as NavHostFragment? ?: return
        val navController = host.navController

        NavigationUI.setupWithNavController(bottomNav, navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
*/
        val navController = this.findNavController(R.id.ActivityMain_NavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.ActivityMainNavigationView, navController)



        /*bottomNav.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                lateinit var selectedFragment: Fragment
                when (item.getItemId()) {
                    R.id.SearchFragment -> selectedFragment =
                        SearchFragment()
                    R.id.CreateCocktailFragment -> selectedFragment =
                        CocktailFragment()

                }

                var fragmentManager = getSupportFragmentManager()
                fragmentManager.beginTransaction().show(selectedFragment).commit()

                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment).commit()
                return true
            }
        })*/

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.ActivityMain_NavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}