package com.jhostinluna.sprint4.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentCreatePersonBinding
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.ui.navigation.ARG_PERSON_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


/**
 *
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CreatePersonFragment : BaseFragment<FragmentCreatePersonBinding>() {

    private lateinit var personIdParam: Integer
    private val viewModel: CreatePersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personIdParam = Integer(it.getInt(ARG_PERSON_ID))
        }
        Log.d("prueba",personIdParam.toString())
    }
    override fun inflateBinding() {
        binding = FragmentCreatePersonBinding.inflate(layoutInflater)
    }
    override fun createViewAfterInflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        setListeners()
    }
    private fun setListeners() {
        binding?.let {bind->
            bind.buttonSave.setOnClickListener {
                if (bind.editTName.text.isNotEmpty()
                    && bind.editTColor.text.isNotEmpty()
                    && bind.editTCity.text.isNotEmpty()
                    && bind.editTNumber.text.isNotEmpty()
                    && bind.editTDate.text.isNotEmpty()
                ){
                    val simpleDateFormat = SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault())

                    viewModel.addPerson(personModel = PersonModel(
                        id = if(personIdParam.toInt() > -1) personIdParam.toInt() else null,
                        name = bind.editTName.text.toString(),
                        color = bind.editTColor.text.toString(),
                        city = bind.editTCity.text.toString(),
                        number = bind.editTNumber.text.toString().toInt(),
                        dateBorn = simpleDateFormat.parse(bind.editTDate.text.toString())
                    ))
                }

            }
        }
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.personMutableStateFlow.collect {person->
                Log.d("DetailPersonFragment", "observeViewModel: $person")
                person?.let {
                    updateUI(person)
                }
            }
        }
    }

    private fun updateUI(person: PersonModel) {
        val simpleDateFormat = SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault())
        binding?.apply {
            editTName.setText(person.name)
            editTColor.setText(person.color)
            editTCity.setText(person.city)
            editTNumber.setText(person.number.toString())
            person.dateBorn?.let {
                if (it.toString().isNotEmpty()) {
                    editTDate.setText(simpleDateFormat.format(it))
                }
            }
        }
    }

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        if (personIdParam > -1) {
            viewModel.loadDetailPerson(personIdParam.toInt())
        }
    }

    override fun configureToolbarAndConfigScreenSections() {
    }

}