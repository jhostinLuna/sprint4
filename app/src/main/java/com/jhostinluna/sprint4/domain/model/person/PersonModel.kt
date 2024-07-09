package com.jhostinluna.sprint4.domain.model.person

import java.util.Date

class PersonModel(
    var id: Int? = null,
    var name: String,
    var color: String,
    var number: Int,
    var city: String,
    var dateBorn: Date? = null
)