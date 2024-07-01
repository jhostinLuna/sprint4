package com.jhostinluna.sprint4.domain.model.person

import java.util.Date

class PersonModel(
    val id: Int? = null,
    val name: String,
    val color: String,
    val number: Int,
    val city: String,
    val dateBorn: Date? = null
)