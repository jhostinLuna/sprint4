package com.jhostinluna.sprint4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentDetailPersonBinding


private const val PERSON_ID = "personID"
/**
 * A simple [Fragment] subclass.
 * Use the [DetailPersonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailPersonFragment : BaseFragment<FragmentDetailPersonBinding>() {

    private var personIdParam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personIdParam = it.getString(PERSON_ID)
        }
    }


    override fun inflateBinding() {
        binding = FragmentDetailPersonBinding.inflate(layoutInflater)
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