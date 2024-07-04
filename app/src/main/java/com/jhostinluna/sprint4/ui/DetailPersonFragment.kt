package com.jhostinluna.sprint4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentDetailPersonBinding
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.ui.navigation.ARG_PERSON_ID
import com.jhostinluna.sprint4.ui.navigation.Screen
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class DetailPersonFragment : BaseFragment<FragmentDetailPersonBinding>() {

    private var personIdParam: String? = null
    private val viewModel: DetailPersonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personIdParam = it.getString(ARG_PERSON_ID)
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
        initListeners()
    }
    private fun initListeners() {
        binding?.buttonSeeDetail?.setOnClickListener {
            findNavController().navigate(route = "${Screen.MapCity.route}/${personIdParam}")
        }
    }
    private fun updateUI(person: PersonModel) {
        binding?.apply {
            itemPersonCity.binding.textVNameValue.text = person.name
            itemPersonColor.binding.textVNameValue.text = person.color
            itemPersonCity.binding.textVNameValue.text = person.city
            // hay formatear fecha que se encuentra en milisegundos en Base de datos local
            itemPersonDateBorn.binding.textVNameValue.text = person.dateBorn.toString()
        }

    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.personMutableStateFlow.collect {person->
                person?.let {
                    updateUI(person)
                }
            }
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        personIdParam?.toInt()?.let { id->
            viewModel.loadDetailPerson(id)
        }

    }

    override fun configureToolbarAndConfigScreenSections() {
        fragmentLayoutWithToolbar()
        showToolbar("Detalle")
    }

}