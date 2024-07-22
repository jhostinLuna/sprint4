package com.jhostinluna.sprint4.ui

import android.location.Address
import androidx.lifecycle.viewModelScope
import com.jhostinluna.sprint4.core.platform.BaseViewModel
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.domain.usecases.GetAddressFromTextUseCase
import com.jhostinluna.sprint4.domain.usecases.GetDetailPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapCityViewModel @Inject constructor(
    private val getDetailPersonUseCase: GetDetailPersonUseCase,
    private val getAddressFromTextUseCase: GetAddressFromTextUseCase
): BaseViewModel() {
    private val _personMutableStateFlow = MutableStateFlow<PersonModel?>(null)
    val personStateFlow = _personMutableStateFlow.asStateFlow()
    private val _addressMutableStateFlow = MutableStateFlow<Address?>(null)
    val addressStateFlow = _addressMutableStateFlow.asStateFlow()

    fun loadDetailPerson(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getDetailPersonUseCase(id).collect { person ->
                _personMutableStateFlow.value = person
            }
        }

    }

    fun loadAddressFromText(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getAddressFromTextUseCase(text).collect {address->
                 if (address.isNotEmpty()) {
                     _addressMutableStateFlow.value = address.first()
                 }

            }
        }
    }
}