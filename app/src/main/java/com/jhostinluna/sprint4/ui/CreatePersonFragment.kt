package com.jhostinluna.sprint4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentCreatePersonBinding


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class CreatePersonFragment : BaseFragment<FragmentCreatePersonBinding>() {



    override fun inflateBinding() {
        binding = FragmentCreatePersonBinding.inflate(layoutInflater)
    }

    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
    }

    override fun observeViewModel() {
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
    }

    override fun configureToolbarAndConfigScreenSections() {
    }

}