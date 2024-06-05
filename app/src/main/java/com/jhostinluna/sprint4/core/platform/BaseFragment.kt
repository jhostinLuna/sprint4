package com.jhostinluna.sprint4.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jhostinluna.sprint4.ui.navigation.MainActivity

abstract class BaseFragment<B : ViewBinding>: Fragment() {
    var binding: B? = null
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflateBinding()
        createViewAfterInflateBinding(inflater,container,savedInstanceState)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewCreatedAfterSetupObserverViewModel(view,savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        configureToolbarAndConfigScreenSections()
    }

    abstract fun inflateBinding()
    abstract fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )
    abstract fun observeViewModel()

    abstract fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?)
    abstract fun configureToolbarAndConfigScreenSections()


}