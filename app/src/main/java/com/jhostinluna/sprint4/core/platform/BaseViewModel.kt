package com.jhostinluna.sprint4.core.platform

import androidx.lifecycle.SavedStateHandle
import com.jhostinluna.sprint4.domain.model.ErrorModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class BaseViewModel constructor(
    val savedStateHandle: SavedStateHandle? = null,
) {

    protected val loadingMutableSharedFlow = MutableSharedFlow<Boolean>(replay = 0)
    val loadingFlow: SharedFlow<Boolean> = loadingMutableSharedFlow

    protected val errorMutableSharedFlow = MutableSharedFlow<ErrorModel>(replay = 0)
    val errorFlow: SharedFlow<ErrorModel> = errorMutableSharedFlow
}