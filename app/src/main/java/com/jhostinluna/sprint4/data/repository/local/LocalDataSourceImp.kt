package com.jhostinluna.sprint4.data.repository.local

import com.jhostinluna.sprint4.data.repository.LocalDataSource
import com.jhostinluna.sprint4.data.repository.local.entities.PersonEntity
import com.jhostinluna.sprint4.domain.model.person.GetPersonsMapper
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import com.jhostinluna.sprint4.domain.model.person.PersonToResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceImp @Inject constructor(
    private val personDAO: PersonDAO
): LocalDataSource {
    override fun getAllPerson(): Flow<List<PersonModel>> = personDAO.getAll().map {listPersonEntity->
        val mapper = GetPersonsMapper()
        listPersonEntity.map { mapper.fromResponse(it) }
    }

    override fun addPerson(personModel: PersonModel) {
        val mapper = PersonToResponse()

        //personDAO.insertAll(*personModel.map { mapper.fromResponse(it) }.toTypedArray())
        personDAO.insertAll(mapper.fromResponse(personModel))
    }

    override fun deletePerson(personModel: PersonModel) {
        val mapper = PersonToResponse()
        personDAO.delete(mapper.fromResponse(personModel))
    }

}