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
import com.jhostinluna.sprint4.ui.CreatePersonFragment
import com.jhostinluna.sprint4.ui.HomeFragment
import com.jhostinluna.sprint4.ui.MapCityFragment
import dagger.hilt.android.AndroidEntryPoint

const val ARG_PERSON_ID = "personID"
@AndroidEntryPoint
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
    fun showToolbar(
        title: String = "",
    ) {
        binding.includeToolbar.toolbarMainactivity.visible()
        binding.includeToolbar.toolbarMainactivity.title = title
    }
    private fun createGraphicDSL() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = Screen.Home.route
        ) {
            fragment<HomeFragment>(Screen.Home.route) {
                label = "List of Characters"
            }
            fragment<DetailPersonFragment>(route = "${Screen.PersonDetail.route}/{$ARG_PERSON_ID}") {
                label = "detail of Person"
                argument(ARG_PERSON_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                    nullable = false
                }
            }
            fragment<CreatePersonFragment>(route = "${Screen.AddPerson.route}/{$ARG_PERSON_ID}") {
                label = "form for add one person"
                argument(ARG_PERSON_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                    nullable = false
                }
            }
            fragment<MapCityFragment>(route = "${Screen.MapCity.route}/{$ARG_PERSON_ID}") {
                label = "Map for show location of city favorite"
                argument(ARG_PERSON_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                    nullable = false
                }
            }
        }
    }
}