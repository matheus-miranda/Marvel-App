package com.example.marvelapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavMain.setupWithNavController(navController)

        // Inform the appBarConfiguration which are the top level fragments
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.charactersFragment, R.id.favoritesFragment, R.id.aboutFragment, R.id.sortFragment)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)

        // Remove up button icon when on top level graph destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination) {
                binding.toolbarMain.setNavigationIcon(R.drawable.ic_back)
            }
        }
    }
}