package com.jhostinluna.sprint4.data.repository

import com.jhostinluna.sprint4.domain.model.person.PersonModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAllPerson(): Flow<List<PersonModel>>

    fun addPerson(personModel: PersonModel): Unit

    fun deletePerson(personModel: PersonModel): Unit

    fun getPersonById(id: Int): Flow<PersonModel>

}
