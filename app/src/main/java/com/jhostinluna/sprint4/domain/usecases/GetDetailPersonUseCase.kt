package com.jhostinluna.sprint4.domain.usecases

import com.jhostinluna.sprint4.domain.interfaces.DataProvider
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailPersonUseCase @Inject constructor(
    private val dataProvider: DataProvider
){
    operator fun invoke(id: Int): Flow<PersonModel>  = dataProvider.getDetailPerson(id)
}
