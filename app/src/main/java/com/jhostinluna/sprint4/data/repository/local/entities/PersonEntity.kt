package com.jhostinluna.sprint4.data.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "persons")
data class PersonEntity (
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    val name: String,
    @ColumnInfo("favorite_color") val favoriteColor: String,
    @ColumnInfo("date_born") val dateBorn: Date? = null,
    @ColumnInfo("favorite_city") val favoriteCity:String,
    @ColumnInfo("favorite_number") val favoriteNumber: Int,
    )