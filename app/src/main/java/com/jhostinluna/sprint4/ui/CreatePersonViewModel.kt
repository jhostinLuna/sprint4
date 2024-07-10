package com.jhostinluna.sprint4.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import com.jhostinluna.sprint4.core.platform.BaseViewModel
import com.jhostinluna.sprint4.data.repository.local.converters.Coordinate
import com.jhostinluna.sprint4.domain.model.error.ErrorModel
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.domain.usecases.CreatePersonUseCase
import com.jhostinluna.sprint4.domain.usecases.GetDetailPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.asDeferred
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class CreatePersonViewModel @Inject constructor(
    private val createPersonUseCase: CreatePersonUseCase,
    private val getDetailPersonUseCase: GetDetailPersonUseCase,
    private val fusedLocationClient: FusedLocationProviderClient,
): BaseViewModel() {

    var person: PersonModel = PersonModel()

    private val _personMutableStateFlow = MutableStateFlow<PersonModel?>(null)
    val personMutableStateFlow = _personMutableStateFlow.asStateFlow()

    private val _locationMutableStateFlow = MutableStateFlow<LatLng?>(null)
    val locationMutableStateFlow = _locationMutableStateFlow.asStateFlow()
    fun addPerson() {
        viewModelScope.launch(Dispatchers.IO) {
            createPersonUseCase(person)
        }

    }
    fun setPersonModel(personModel: PersonModel) {
        person.apply {
            name = personModel.name
            color = personModel.color
            id = personModel.id
            city = personModel.city
            number = personModel.number
            dateBorn = personModel.dateBorn
        }
    }
    fun loadDetailPerson(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getDetailPersonUseCase(id).collect { person ->
                _personMutableStateFlow.value = person
            }
        }

    }
    @SuppressLint("MissingPermission")
    fun initFusedLocationListener() {
        viewModelScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis{
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    Log.d("prueba", "Viewmodel -> $location")
                    if (location != null) {
                        _locationMutableStateFlow.value =
                            LatLng(location.latitude, location.longitude)
                    }
                }.addOnFailureListener {
                    Log.d("prueba", "Viewmodel -> $it")
                }
            }
            println("Completed in Time $time ms")
        }
    }
    @SuppressLint("MissingPermission")
    fun getLocation() {

        viewModelScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis{
                val task = fusedLocationClient.lastLocation
                val deferred = task.asDeferred()
                val deferredLocation = deferred.await()
                val result = task.result
                if (result != null) {
                    Log.d("prueba", "Viewmodel -> $result")
                    _locationMutableStateFlow.value = LatLng(result.latitude, result.longitude)
                    person?.coordinate = Coordinate(result.latitude.toString(), result.longitude.toString())
                } else {
                    Log.d("prueba", "Viewmodel -> NULL")
                    errorMutableSharedFlow.emit(ErrorModel(message = "No se pudo obtener la ubicación"))
                }
            }
            println("Completed in Time $time ms")
        }
    }
}
