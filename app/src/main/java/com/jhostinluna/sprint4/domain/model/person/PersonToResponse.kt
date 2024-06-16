package com.jhostinluna.sprint4.domain.model.person

import com.jhostinluna.sprint4.data.repository.local.entities.PersonEntity
import com.jhostinluna.sprint4.domain.model.ResponseMapper

class PersonToResponse: ResponseMapper<PersonModel,PersonEntity> {
    override fun fromResponse(response: PersonModel): PersonEntity = PersonEntity(
        uid = response.id,
        name = response.name,
        favoriteColor = response.color,
        favoriteCity = response.city,
        favoriteNumber = response.number,
        dateBorn = response.dateBorn
    )
}