package com.jhostinluna.sprint4.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jhostinluna.sprint4.core.platform.BaseViewModel
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.domain.usecases.CreatePersonUseCase
import com.jhostinluna.sprint4.domain.usecases.GetDetailPersonUseCase
import com.jhostinluna.sprint4.domain.usecases.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePersonViewModel @Inject constructor(
    private val createPersonUseCase: CreatePersonUseCase,
    private val getDetailPersonUseCase: GetDetailPersonUseCase
): BaseViewModel() {

    var person: PersonModel? = null

    private val _personMutableStateFlow = MutableStateFlow<PersonModel?>(null)
    val personMutableStateFlow = _personMutableStateFlow.asStateFlow()
    fun addPerson(personModel: PersonModel) {
        viewModelScope.launch(Dispatchers.IO) {
            createPersonUseCase(personModel)
        }

    }
    fun loadDetailPerson(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getDetailPersonUseCase(id).collect { person ->
                _personMutableStateFlow.value = person
            }
        }

    }
}