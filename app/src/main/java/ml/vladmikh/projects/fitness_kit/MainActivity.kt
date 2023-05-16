package ml.vladmikh.projects.fitness_kit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import ml.vladmikh.projects.shopapp.R
import ml.vladmikh.projects.shopapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        navController= Navigation.findNavController(this,R.id.navHostFragment)
        setupActionBarWithNavController(navController)
        setupWithNavController(binding.bottomNavigation,navController)
    }
}