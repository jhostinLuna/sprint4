package com.jhostinluna.sprint4.domain.model.person

import com.jhostinluna.sprint4.data.repository.local.entities.PersonEntity
import com.jhostinluna.sprint4.domain.model.ResponseMapper

class GetPersonsMapper: ResponseMapper<PersonEntity,PersonModel> {
    override fun fromResponse(response: PersonEntity): PersonModel = PersonModel(
        id = response.uid,
        name = response.name,
        color = response.favoriteColor,
        number = response.favoriteNumber,
        city = response.favoriteCity,
        dateBorn = response.dateBorn,
        coordinate = response.coordinates
    )
}