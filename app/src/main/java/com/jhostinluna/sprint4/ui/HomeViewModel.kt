package com.jhostinluna.sprint4.ui

import androidx.lifecycle.viewModelScope
import com.jhostinluna.sprint4.core.platform.BaseViewModel
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.domain.usecases.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPersonsUseCase: GetPersonUseCase
): BaseViewModel() {
    private val _personListMutableStatFlow: MutableStateFlow<List<PersonModel>> = MutableStateFlow<List<PersonModel>>(
        emptyList()
    )
    val personListMutableStatFlow: StateFlow<List<PersonModel>> = _personListMutableStatFlow

    init {
        getPersonList()
    }
    fun getPersonList() {
        viewModelScope.launch(Dispatchers.IO) {
            getPersonsUseCase().collect() {
                _personListMutableStatFlow.value = it
            }
        }

    }
}