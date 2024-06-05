package com.jhostinluna.sprint4.core.navigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.core.extensions.gone
import com.jhostinluna.sprint4.core.extensions.visible
import com.jhostinluna.sprint4.databinding.ActivityMainBinding

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
    }
    fun hideToolbarLayout() {
        binding.includeToolbar.toolbarMainactivity.gone()
    }

    fun showToolbarLayout() {
        binding.includeToolbar.toolbarMainactivity.visible()
    }
}