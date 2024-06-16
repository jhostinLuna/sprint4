package com.jhostinluna.sprint4.domain.model

interface ResponseMapper<E, M> {

    fun fromResponse(response: E): M
}