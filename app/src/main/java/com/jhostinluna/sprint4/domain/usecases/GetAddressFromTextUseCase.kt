package com.jhostinluna.sprint4.domain.usecases

import android.location.Address
import com.jhostinluna.sprint4.domain.interfaces.DataProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAddressFromTextUseCase @Inject constructor(
    private val dataProvider: DataProvider
) {
    operator fun invoke(text: String): Flow<List<Address>> = dataProvider.getAddressFromText(text)
}
