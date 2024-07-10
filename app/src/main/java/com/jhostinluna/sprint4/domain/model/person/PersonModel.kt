package com.jhostinluna.sprint4.domain.model.person

import com.jhostinluna.sprint4.data.repository.local.converters.Coordinate
import java.util.Date

class PersonModel(
    var id: Int? = null,
    var name: String = "",
    var color: String = "",
    var number: Int? = null,
    var city: String = "",
    var dateBorn: Date? = null,
    var coordinate: Coordinate? = null
)