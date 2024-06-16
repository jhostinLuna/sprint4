package com.jhostinluna.sprint4.domain.interfaces

import com.jhostinluna.sprint4.domain.model.person.PersonModel
import kotlinx.coroutines.flow.Flow

interface DataProvider {

    fun getAllPersons(): Flow<List<PersonModel>>

    fun addPerson(person: PersonModel): Unit

    fun deletePerson(person: PersonModel): Unit


}
