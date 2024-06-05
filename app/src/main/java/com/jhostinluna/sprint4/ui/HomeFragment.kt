package com.jhostinluna.sprint4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentHomeBinding



/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun inflateBinding() {
        binding = FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun configureToolbarAndConfigScreenSections() {
        TODO("Not yet implemented")
    }

}