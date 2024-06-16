package com.jhostinluna.sprint4.domain.usecases

import com.jhostinluna.sprint4.domain.interfaces.DataProvider
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import javax.inject.Inject

class CreatePersonUseCase @Inject constructor(
    private val dataProvider: DataProvider
) {
    operator fun invoke(person: PersonModel): Unit = dataProvider.addPerson(person)
}