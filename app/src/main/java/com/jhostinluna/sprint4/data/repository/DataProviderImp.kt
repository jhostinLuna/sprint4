package com.jhostinluna.sprint4.data.repository

import com.jhostinluna.sprint4.data.repository.local.entities.PersonEntity
import com.jhostinluna.sprint4.domain.interfaces.DataProvider
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataProviderImp @Inject constructor(
    private val localDataSource: LocalDataSource
): DataProvider {
    override fun getAllPersons(): Flow<List<PersonModel>> {
        return localDataSource.getAllPerson()
    }

    override fun addPerson(person: PersonModel) {
        localDataSource.addPerson(person)
    }

    override fun deletePerson(person: PersonModel) {
        localDataSource.deletePerson(person)
    }

    override fun getDetailPerson(id: Int): Flow<PersonModel> {
        return localDataSource.getPersonById(id)
    }

}
