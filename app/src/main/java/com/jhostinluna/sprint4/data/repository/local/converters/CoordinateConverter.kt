package com.jhostinluna.sprint4.data.repository.local.converters

import androidx.room.TypeConverter

class CoordinateConverter {
    @TypeConverter
    fun fromCoordinate(coords: Coordinate?): String? {
        return coords?.let { "${it.latitude},${it.longitude}" } ?: null
    }

    @TypeConverter
    fun toCoordinate(data: String?): Coordinate? {
        val parts = data?.split(",")
        return parts?.let { Coordinate(parts[0], parts[1]) } ?: null
    }
}