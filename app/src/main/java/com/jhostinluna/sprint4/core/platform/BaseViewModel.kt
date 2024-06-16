package com.jhostinluna.sprint4.core.platform

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jhostinluna.sprint4.domain.model.error.ErrorModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


abstract class BaseViewModel constructor(
    val savedStateHandle: SavedStateHandle? = null,
): ViewModel() {

    protected val loadingMutableSharedFlow = MutableSharedFlow<Boolean>(replay = 0)
    val loadingFlow: SharedFlow<Boolean> = loadingMutableSharedFlow

    protected val errorMutableSharedFlow = MutableSharedFlow<ErrorModel>(replay = 0)
    val errorFlow: SharedFlow<ErrorModel> = errorMutableSharedFlow


}