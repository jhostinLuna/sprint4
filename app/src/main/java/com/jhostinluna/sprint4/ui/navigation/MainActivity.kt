package com.jhostinluna.sprint4.ui.navigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.core.extensions.gone
import com.jhostinluna.sprint4.core.extensions.visible
import com.jhostinluna.sprint4.databinding.ActivityMainBinding
import com.jhostinluna.sprint4.ui.DetailPersonFragment
import com.jhostinluna.sprint4.ui.FormPersonFragment
import com.jhostinluna.sprint4.ui.HomeFragment
import com.jhostinluna.sprint4.ui.MapCityFragment

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding
        get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        createGraphicDSL()
    }
    fun hideToolbarLayout() {
        binding.includeToolbar.toolbarMainactivity.gone()
    }

    fun showToolbarLayout() {
        binding.includeToolbar.toolbarMainactivity.visible()
    }
    private fun createGraphicDSL() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = "home"
        ) {
            fragment<HomeFragment>("home") {
                label = "List of Characters"
            }
            fragment<DetailPersonFragment>(route = "detail/{personID}") {
                label = "detail of Person"
                argument("personID") {
                    type = NavType.IntType
                    defaultValue = -1
                    nullable = false
                }
            }
            fragment<FormPersonFragment>(route = "form") {
                label = "form for add one person"
            }
            fragment<MapCityFragment>(route = "map/{personID}") {
                label = "Map for show location of city favorite"
                argument("personID") {
                    type = NavType.IntType
                    defaultValue = -1
                    nullable = false
                }
            }
        }
    }
}