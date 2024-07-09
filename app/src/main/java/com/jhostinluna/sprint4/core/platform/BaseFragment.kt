package com.jhostinluna.sprint4.core.platform

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.jhostinluna.sprint4.ui.dialogs.DatePickerFragment
import com.jhostinluna.sprint4.ui.navigation.MainActivity

abstract class BaseFragment<B : ViewBinding>: Fragment(), MainActivity.ToolbarListener {
    var binding: B? = null
    private lateinit var mainActivity: MainActivity
    var menuClickListener: ((itemId: Int) -> Boolean)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        mainActivity.toolbarListener = this
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

    fun fragmentLayoutWithToolbar() {
        mainActivity.showToolbarLayout()
    }
    fun hideToolbar() {
        mainActivity.hideToolbarLayout()
    }
    fun showToolbar(
        title: String = "",
        showEditIcon: Boolean = false,
    ) {
        mainActivity.showToolbar(
            title = title,
            showEditIcon = showEditIcon
        )
    }
    fun showDatePickerDialog(datePickerListener: DatePickerFragment.DatePickerListener) {
        mainActivity.showDatePickerDialog(datePickerListener)
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

    override fun onclickEditIcon(){
        Log.d("prueba","onclickEditIcon")
    }

}