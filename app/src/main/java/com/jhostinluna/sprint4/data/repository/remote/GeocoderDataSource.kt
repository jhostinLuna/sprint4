package com.jhostinluna.sprint4.data.repository.remote

import android.location.Address
import android.location.Geocoder
import javax.inject.Inject

class GeocoderDataSource @Inject constructor(
    private val geocoder: Geocoder
) {
    fun getFromLocationName(locationName: String): List<Address> {
        return geocoder.getFromLocationName(locationName, 1)?.toList() ?: emptyList()

    }
}