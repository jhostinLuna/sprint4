package com.jhostinluna.sprint4.data.repository

import android.location.Address
import com.jhostinluna.sprint4.data.repository.local.entities.PersonEntity
import com.jhostinluna.sprint4.data.repository.remote.GeocoderDataSource
import com.jhostinluna.sprint4.domain.interfaces.DataProvider
import com.jhostinluna.sprint4.domain.model.person.PersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataProviderImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val geocoderDataSource: GeocoderDataSource
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

    override fun getAddressFromText(text: String): Flow<List<Address>> = flow {
        emit(geocoderDataSource.getFromLocationName(text))
    }

    // Geocoder


}
