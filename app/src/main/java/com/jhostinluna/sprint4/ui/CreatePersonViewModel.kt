package com.jhostinluna.sprint4.ui

import androidx.lifecycle.viewModelScope
import com.jhostinluna.sprint4.core.platform.BaseViewModel
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.domain.usecases.CreatePersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePersonViewModel @Inject constructor(
    private val createPersonUseCase: CreatePersonUseCase
): BaseViewModel() {

    var person: PersonModel? = null
    fun addPerson(personModel: PersonModel) {
        viewModelScope.launch(Dispatchers.IO) {
            createPersonUseCase(personModel)
        }

    }

}