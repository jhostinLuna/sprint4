package com.jhostinluna.sprint4.ui.navigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.core.extensions.gone
import com.jhostinluna.sprint4.core.extensions.visible
import com.jhostinluna.sprint4.databinding.ActivityMainBinding
import com.jhostinluna.sprint4.ui.DetailPersonFragment
import com.jhostinluna.sprint4.ui.CreatePersonFragment
import com.jhostinluna.sprint4.ui.HomeFragment
import com.jhostinluna.sprint4.ui.MapCityFragment
import com.jhostinluna.sprint4.ui.dialogs.DatePickerFragment
import dagger.hilt.android.AndroidEntryPoint

const val ARG_PERSON_ID = "personID"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var _binding: ActivityMainBinding
    var toolbarListener: ToolbarListener? = null
    val datePickerDialog: DatePickerFragment = DatePickerFragment()
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
        inflateToolbar()
    }
    private fun inflateToolbar() {
        setSupportActionBar(binding.includeToolbar.toolbarMainActivity)
        /**
        setSupportActionBar(binding.includeToolbar.toolbarMainActivity)
        menuInflater.inflate(R.menu.menu_toolbar,binding.includeToolbar.toolbarMainActivity.menu)
        binding.includeToolbar.toolbarMainActivity.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.editPerson -> {
                    (supportFragmentManager.findFragmentById(R.id.navHostFragment) as? BaseFragment<*>)
                        ?.menuClickListener?.invoke(it.itemId) ?: false
                    true
                }
                else -> false
            }

        }
        **/
        /**
         * Si deseas agregar compatibilidad de navegación a la barra de acciones predeterminada,
         * llama a setupActionBarWithNavController() desde el método onCreate() de tu actividad principal,
         * como se muestra a continuación. Ten en cuenta que debes declarar tu AppBarConfiguration
         * fuera de onCreate(), ya que también lo usarás cuando anules onSupportNavigateUp():
         */

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.includeToolbar.toolbarMainActivity
            .setupWithNavController(navController,appBarConfiguration)
    }
    fun hideToolbarLayout() {
        binding.includeToolbar.toolbarMainActivity.gone()
    }

    fun showToolbarLayout() {
        binding.includeToolbar.toolbarMainActivity.visible()
    }
    fun showToolbar(
        title: String = "",
        showEditIcon: Boolean = false,
    ) {
        binding.includeToolbar.toolbarMainActivity.visible()
        binding.includeToolbar.toolbarMainActivity.title = title
        binding.includeToolbar.toolbarMainActivity.menu.findItem(R.id.editPerson).isVisible = showEditIcon
    }
    private fun createGraphicDSL() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph = navController.createGraph(
            startDestination = Screen.Home.route
        ) {
            fragment<HomeFragment>(route = Screen.Home.route) {
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.editPerson -> {
                toolbarListener?.onclickEditIcon()
                return true
            }
            else -> return false
        }
    }
    fun showDatePickerDialog(datePickerListener: DatePickerFragment.DatePickerListener) {
        if (supportFragmentManager.findFragmentByTag("datePicker") == null ) {
            datePickerDialog.show(supportFragmentManager, "datePicker")
            datePickerDialog.setDatePickerListeners(listener = datePickerListener)
        }

    }
    interface ToolbarListener {
        fun onclickEditIcon()
    }
}