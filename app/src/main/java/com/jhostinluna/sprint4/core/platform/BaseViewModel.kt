package com.jhostinluna.sprint4.core.platform

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhostinluna.sprint4.domain.model.error.ErrorModel
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.domain.usecases.GetPersonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel(
    val savedStateHandle: SavedStateHandle? = null,
): ViewModel() {

    protected val loadingMutableSharedFlow = MutableSharedFlow<Boolean>(replay = 0)
    val loadingFlow: SharedFlow<Boolean> = loadingMutableSharedFlow

    protected val errorMutableSharedFlow = MutableSharedFlow<ErrorModel>(replay = 0)


}