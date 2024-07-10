package com.jhostinluna.sprint4.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jhostinluna.sprint4.core.extensions.checkLocationPermission
import com.jhostinluna.sprint4.core.platform.BaseFragment
import com.jhostinluna.sprint4.databinding.FragmentCreatePersonBinding
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.ui.dialogs.DatePickerFragment
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
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d("prueba", "onCreateView: tiene permiso")
            viewModel.getLocation()
        } else {
            Log.d("prueba", "onCreateView: no tiene permiso")
        }
    }
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
            bind.editTDate.setOnClickListener {
                showDatePickerDialog(object : DatePickerFragment.DatePickerListener {
                    override fun onDateSelected(year: Int, month: Int, day: Int) {
                        bind.editTDate.setText("$day/$month/$year")
                    }

                })
            }
            bind.buttonSave.setOnClickListener {
                if (bind.editTName.text.isNotEmpty()
                    && bind.editTColor.text.isNotEmpty()
                    && bind.editTCity.text.isNotEmpty()
                    && bind.editTNumber.text.isNotEmpty()
                    && bind.editTDate.text.isNotEmpty()
                ){
                    val simpleDateFormat = SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault())

                    viewModel.setPersonModel(personModel = PersonModel(
                        id = if(personIdParam.toInt() > -1) personIdParam.toInt() else null,
                        name = bind.editTName.text.toString(),
                        color = bind.editTColor.text.toString(),
                        city = bind.editTCity.text.toString(),
                        number = bind.editTNumber.text.toString().toInt(),
                        dateBorn = simpleDateFormat.parse(bind.editTDate.text.toString())
                    ))
                    viewModel.addPerson()
                }

            }
            bind.buttonLocation.setOnClickListener {
                if (verifyLocationPermission()) {
                    Log.d("prueba", "verifyLocationPermission: tiene permiso")
                    viewModel.getLocation()
                } else {
                    Log.d("prueba", "verifyLocationPermission: no tiene permiso")
                    requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
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
        lifecycleScope.launch {
            viewModel.locationMutableStateFlow.collect {
                Toast.makeText(context, "¡Ubicación obtenida con exito!", Toast.LENGTH_SHORT).show()
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
    private fun verifyLocationPermission(): Boolean = context?.checkLocationPermission() == PackageManager.PERMISSION_GRANTED

    override fun viewCreatedAfterSetupObserverViewModel(view: View, savedInstanceState: Bundle?) {
        if (personIdParam > -1) {
            viewModel.loadDetailPerson(personIdParam.toInt())
        }


    }

    override fun configureToolbarAndConfigScreenSections() {
    }

}