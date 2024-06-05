package com.jhostinluna.sprint4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentFormPersonBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FormPersonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormPersonFragment : BaseFragment<FragmentFormPersonBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_person, container, false)
    }

    override fun inflateBinding() {
        TODO("Not yet implemented")
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